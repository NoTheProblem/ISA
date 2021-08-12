package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.dto.WorkingHoursDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.PharmacyMapper;
import ftn.isa.pharmacy.mapper.impl.DermatologistMapperImpl;
import ftn.isa.pharmacy.mapper.impl.ExaminationMapperImpl;
import ftn.isa.pharmacy.mapper.impl.WorkingHoursMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final PatientRepository patientRepository;
    private final MailServiceImpl mailService;
    private final DermatologistMapperImpl dermatologistMapper;
    private final WorkingHoursMapperImpl workingHoursMapper;
    private final WorkingHoursRepository workingHoursRepository;
    private final WorkingHoursPharmacistRepository workingHoursPharmacistRepository;
    private final ExaminationRepository examinationRepository;
    private final ExaminationMapperImpl examinationMapper;
    private final PharmacyMapper pharmacyMapper;
    private final MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository;
    private final DermatologistRepository dermatologistRepository;
    private final PharmacistRepository pharmacistRepository;
    private final AbsenceRequestRepository absenceRequestRepository;
    private final CounselingRepository counselingRepository;


    // https://www.vojtechruzicka.com/field-dependency-injection-considered-harmful/#gatsby-focus-wrapper
    @Autowired
    public PharmacyServiceImpl(CounselingRepository counselingRepository, AbsenceRequestRepository absenceRequestRepository ,WorkingHoursPharmacistRepository workingHoursPharmacistRepository ,PharmacyRepository pharmacyRepository, PharmacyAdminRepository pharmacyAdminRepository, PatientRepository patientRepository, MailServiceImpl mailService, DermatologistMapperImpl dermatologistMapper, WorkingHoursMapperImpl workingHoursMapper, WorkingHoursRepository workingHoursRepository, ExaminationRepository examinationRepository, ExaminationMapperImpl examinationMapper, PharmacyMapper pharmacyMapper, MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository, DermatologistRepository dermatologistRepository, PharmacistRepository pharmacistRepository) {
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.patientRepository = patientRepository;
        this.mailService = mailService;
        this.dermatologistMapper = dermatologistMapper;
        this.workingHoursMapper = workingHoursMapper;
        this.workingHoursRepository = workingHoursRepository;
        this.examinationRepository = examinationRepository;
        this.examinationMapper = examinationMapper;
        this.pharmacyMapper = pharmacyMapper;
        this.medicineQuantityPharmacyRepository = medicineQuantityPharmacyRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.workingHoursPharmacistRepository = workingHoursPharmacistRepository;
        this.absenceRequestRepository = absenceRequestRepository;
        this.counselingRepository  = counselingRepository;
    }

    @Override
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Pharmacy getById(Long id) {
        Optional<Pharmacy> pharmacyOptional =  pharmacyRepository.findById(id);
        if(pharmacyOptional.isPresent()){
            return pharmacyOptional.get();
        }
        throw new ResourceConflictException(1L,"Ne postoji apoteka");

    }

    @Override
    public Pharmacy getPharmacyByAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            return pharmacyAdmin.getPharmacy();
        }
        throw new ResourceConflictException(1L,"Ne postoje promocije/akcije");

    }

    @Override
    public Boolean subscribe(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            Set<Pharmacy> promotionSet = patient.getSubscribedPharmacies();
            Pharmacy pharmacy = pharmacyRepository.getOne(id);
            promotionSet.add(pharmacy);
            patient.setSubscribedPharmacies(promotionSet);
            patientRepository.saveAndFlush(patient);
            mailService.newSubscriptionForPromotion(pharmacy,patient);
            return true;
        }
        throw new ResourceConflictException(1L,"Ne postoji pacijent");

    }

    @Override
    public Set<Dermatologist> getDermaByPhaID(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        return pharmacy.getDermatologists();
    }

    @Override
    public Set<Pharmacist> getPharmaByPhaID(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        return pharmacy.getPharmacists();
    }

    @Override
    public Collection<DermatologistDto> getDermaForPhaAdmin() {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Set<Dermatologist> dermatologists = pharmacy.getDermatologists();
        Set<DermatologistDto> dermatologistDtos = new HashSet<>();
        for(Dermatologist d : dermatologists){
            DermatologistDto dermatologistDto = dermatologistMapper.entity2Bean(d);
            List<WorkingHoursDTO> workingHoursDTOS = new Stack<>();
            WorkingHours wk = workingHoursRepository.getWorkingHoursByPharmacyAndDermatologist(pharmacy,d);
            workingHoursDTOS.add(workingHoursMapper.entity2Bean(wk));
            dermatologistDto.setWorkingHours(workingHoursDTOS);
            List<ExaminationDto> examinationDtos = new Stack<>();
            examinationDtos.addAll(examinationMapper.entity2Bean(examinationRepository.findAllByDermatologist(d)));
            dermatologistDto.setExaminations(examinationDtos);
            dermatologistDtos.add(dermatologistDto);
        }
        return dermatologistDtos;
    }


    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            return pharmacyAdminOptional.get();
        }
        throw new ResourceConflictException(1L,"Ne postoji administrator apoteke!");
    }

    @Override
    public void addPharmacy(PharmacyDto pharmacyDto){
        Pharmacy pharmacy = pharmacyMapper.bean2Entity(pharmacyDto);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public void updatePharmacyInfo(PharmacyDto pharmacyDto) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyMapper.bean2Entity(pharmacyDto);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public Collection<Medicine> getAvailableMedicines(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        //Collection<MedicineQuantityPharmacy> medicationQuantities = pharmacy.getMedicationQuantities();
        List<MedicineQuantityPharmacy> medicationQuantities = medicineQuantityPharmacyRepository.findAllByPharmacy(pharmacy);
        Collection<Medicine> medicines = new HashSet<>();
        for (MedicineQuantityPharmacy medQuan:medicationQuantities) {
            if(medQuan.getQuantity()>0){
                medicines.add(medQuan.getMedicine());
            }
        }
        return medicines;
    }

    @Override
    public Collection<Examination> getAvailableExaminations(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        return examinationRepository.findAllByPharmacyAndIsFree(pharmacy,true);

    }

    @Override
    public void deleteEmployee(Long id, String type) {
        PharmacyAdmin pharmacyAdmin  =getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Date today = new Date();
        if(dermatologistRepository.existsById(id)){
            Dermatologist dermatologist = dermatologistRepository.getOne(id);
            Set<Examination> examinations = dermatologist.getExaminations();
            for (Examination examination:examinations) {
                if(examination.getDate().after(today)){
                    throw new ResourceConflictException(1L,"Greska!");
                }
            }
            Set<Dermatologist> dermatologists = pharmacy.getDermatologists();
            dermatologists.remove(dermatologist);
            pharmacy.setDermatologists(dermatologists);
        }else {
            Pharmacist pharmacist = pharmacistRepository.getOne(id);
            Set<Counseling> counselings = pharmacist.getCounselings();
            for (Counseling counseling: counselings){
                if(counseling.getDate().after(today)){
                    throw new ResourceConflictException(1L,"Greska!");
                }
            }
            Set<Pharmacist> pharmacists = pharmacy.getPharmacists();
            pharmacists.remove(pharmacist);
            pharmacy.setPharmacists(pharmacists);

        }
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public Collection<Pharmacy> getAvailablePharmacies(String date, String time) throws ParseException {
        Collection<Pharmacy> freePharmacies = new HashSet<>();
        Collection<Pharmacy> allPharmacies = pharmacyRepository.findAll();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateParse = formatter.parse(date);
        int dayOfWeekInt = dateParse.getDay();
        int hours = Integer.parseInt(time.substring(0,2));
        int minutes = Integer.parseInt(time.substring(3));
        dateParse.setHours(hours);
        dateParse.setMinutes(minutes);
        System.out.println(dateParse);

        Collection<WorkingHoursPharmacist> workingHoursPharmacists = workingHoursPharmacistRepository.findByWorkDay(dayOfWeek(dayOfWeekInt));
        for (WorkingHoursPharmacist wk:workingHoursPharmacists){
            System.out.println("Manje" + wk.getStartTime());
            Time help = new Time(0);
            help.setHours(hours);
            help.setMinutes(minutes);
            System.out.println("Pomoc" + help);
            System.out.println("Manje" + wk.getStartTime());
            System.out.println("Vece" + wk.getEndTime());
            List<AbsenceRequest> absenceRequests = absenceRequestRepository.
                    findAllByEmployeeIdAndStartDateBeforeAndEndDateAfterAndStatusIsLike(wk.getPharmacist().getId(),dateParse,dateParse, "Odobreno");
            if(absenceRequests.size()!=0){
                continue;
            }
            Date today = new Date();
            if(dateParse.before(today)){
                throw new ResourceConflictException(1L,"Zakazivanje u proslost!");
            }

            boolean hasAppointment = false;
            Collection<Counseling> scheduledCounseling = counselingRepository.findAllByDateAndPharmacist(dateParse, wk.getPharmacist());;
            if (scheduledCounseling.size() != 0) {continue;}

            if (wk.getStartTime().before(help) && wk.getEndTime().after(help)) {
                freePharmacies.add(wk.getPharmacy());
            }
        }







        return freePharmacies;
    }

    String dayOfWeek(int day){
        switch(day) {
            case 1:
                return "Ponedeljak";
            case 2:
                return "Utorak";

            case 3:
                return "Sreda";
            case 4:
                return "Cetvrtak";

            case 5:
                return "Petak";

            case 6:
                return "Subota";
            default:
                return "Nedelja";
        }

    }

    @Override
    public List<Pharmacy> getAllPharmacyForMedicine(Long id) {
        List<Pharmacy> pharmacies = new Stack<>();
        System.out.println(id);
        List<MedicineQuantityPharmacy> mqps= medicineQuantityPharmacyRepository.findByQuantityAndMedicineID(0,id);
        for (MedicineQuantityPharmacy medicineQuantityPharmacy: mqps) {
            pharmacies.add(medicineQuantityPharmacy.getPharmacy());
        }
        return pharmacies;
    }


}
