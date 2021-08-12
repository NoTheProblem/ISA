package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.CounselingRepository;
import ftn.isa.pharmacy.repository.PatientRepository;
import ftn.isa.pharmacy.repository.ReservationRepository;
import ftn.isa.pharmacy.service.CounselingService;
import ftn.isa.pharmacy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final PatientRepository patientRepository;


    @Autowired
    public ReservationServiceImpl(PatientRepository patientRepository, ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Collection<Reservation> getAllReservedMedicineForPatient(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        System.out.println(((User) authentication.getPrincipal()).getId());
        Patient patient = patientOptional.get();
        return reservationRepository.getAllByPickedUpAndPatient(false, patient);

    }


}
