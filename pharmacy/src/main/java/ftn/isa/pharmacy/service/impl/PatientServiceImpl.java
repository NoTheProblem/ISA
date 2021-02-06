package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.ReservationDto;
import ftn.isa.pharmacy.mapper.MedicineMapper;
import ftn.isa.pharmacy.mapper.ReservationMapper;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final MedicineMapper medicineMapper;
    private final ReservationMapper reservationMapper;
    private final PatientRepository patientRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final ReservationRepository reservationRepository;
    private final MedicineQuantityReservationRepository medicineQuantityReservationRepository;
    private final MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository;


    @Autowired
    public PatientServiceImpl(MedicineMapper medicineMapper, PatientRepository patientRepository, PharmacyRepository pharmacyRepository, ReservationRepository reservationRepository, ReservationMapper reservationMapper, MedicineQuantityReservationRepository medicineQuantityReservationRepository, MedicineRepository medicineRepository, MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository) {
        this.medicineMapper = medicineMapper;
        this.patientRepository = patientRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.medicineQuantityReservationRepository = medicineQuantityReservationRepository;
        this.medicineRepository = medicineRepository;
        this.medicineQuantityPharmacyRepository = medicineQuantityPharmacyRepository;
    }

    @Override
    public void addAllergy(MedicineDto medicineDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Patient patient = (Patient) authentication.getPrincipal(); --> ovo radi, ali ne povuce allergicMedicines jer je lazy
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Medicine medicine = medicineMapper.bean2Entity(medicineDto);
            Patient patient = patientOptional.get();
            patient.getAllergicMedicines().add(medicine);
            patientRepository.saveAndFlush(patient);
        }
    }

    @Override
    public void addReservation(ReservationDto reservationDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(reservationDto.getPharmacyId());
        Pharmacy pharmacy= pharmacyOptional.get();
        if (patientOptional.isPresent()) {
            Reservation reservation = reservationMapper.bean2Entity(reservationDto);
            Patient patient = patientOptional.get();
            Optional<Medicine> medicineOptional = medicineRepository.findById(reservationDto.getMedicationId());
            Reservation reservation1 = new Reservation(null, reservationDto.getPickedUp(),reservation.getEndDate(), reservationDto.getPrice(), pharmacy, patient);
            reservationRepository.saveAndFlush(reservation1);

            if (medicineOptional.isPresent() && pharmacyOptional.isPresent()) {

                Medicine medicine = medicineOptional.get();
                MedicineQuantityReservation medicineQuantityReservation = new MedicineQuantityReservation (null,  1,  medicine, reservation1);
                medicineQuantityReservationRepository.saveAndFlush(medicineQuantityReservation);
                List<MedicineQuantityPharmacy> mqp =  medicineQuantityPharmacyRepository.findByPharmacyIdAndMedicineId(reservationDto.getMedicationId(), reservationDto.getPharmacyId());
                mqp.get(0).setQuantity(mqp.get(0).getQuantity()-1);
                patient.getReservations().add(reservation1);
                patientRepository.saveAndFlush(patient);
            }
        }
    }

}
