package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.impl.PharmacistMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PharmacyMapperImpl;
import ftn.isa.pharmacy.mapper.impl.WorkingHoursPharmacistMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.AuthorityService;
import ftn.isa.pharmacy.service.PharmacistService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final PharmacistMapperImpl pharmacistMapper;
    private final PharmacyMapperImpl pharmacyMapper;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final PharmacyRepository pharmacyRepository;
    private final WorkingHoursPharmacistMapperImpl workingHoursPharmacistMapper;
    private final WorkingHoursPharmacistRepository workingHoursPharmacistRepository;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;
    private final AbsenceRequestRepository absenceRequestRepository;
    private final CounselingRepository counselingRepository;

    public PharmacistServiceImpl(CounselingRepository counselingRepository, AbsenceRequestRepository absenceRequestRepository, PharmacistRepository pharmacistRepository, PharmacistMapperImpl pharmacistMapper, PharmacyMapperImpl pharmacyMapper, PharmacyAdminRepository pharmacyAdminRepository, PharmacyRepository pharmacyRepository, WorkingHoursPharmacistMapperImpl workingHoursPharmacistMapper, WorkingHoursPharmacistRepository workingHoursPharmacistRepository, AuthorityService authorityService, PasswordEncoder passwordEncoder) {
        this.pharmacistRepository = pharmacistRepository;
        this.pharmacistMapper = pharmacistMapper;
        this.pharmacyMapper = pharmacyMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.workingHoursPharmacistMapper = workingHoursPharmacistMapper;
        this.workingHoursPharmacistRepository = workingHoursPharmacistRepository;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
        this.absenceRequestRepository = absenceRequestRepository;
        this.counselingRepository = counselingRepository;

    }

    @Override
    public Collection<PharmacistDTO> getAll() {
        Collection<Pharmacist> pharmacists = pharmacistRepository.findAll();
        Collection<PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for (Pharmacist pharmacist: pharmacists) {
            PharmacistDTO pharmacistDTO = pharmacistMapper.entity2Bean(pharmacist);
            pharmacistDTO.setPharmacy(pharmacyMapper.entity2Bean(pharmacist.getPharmacy()));
            pharmacistDTOS.add(pharmacistDTO);
        }
        return pharmacistDTOS;
    }

    @Override
    public Collection<PharmacistDTO> getAllFree() {
        return pharmacistMapper.entity2Bean(pharmacistRepository.findAllByPharmacyIsNull());
    }

    @Override
    public void addToPharmacy(PharmacistDTO pharmacistDTO) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Pharmacist pharmacist = pharmacistRepository.getOne(pharmacistDTO.getId());
        Set<Pharmacist> pharmacistSet = pharmacy.getPharmacists();
        pharmacistSet.add(pharmacist);
        pharmacy.setPharmacists(pharmacistSet);
        pharmacyRepository.save(pharmacy);
        pharmacist.setPharmacy(pharmacy);
        pharmacistRepository.save(pharmacist);
    }

    @Override
    public void registerToPharmacy(PharmacistDTO pharmacistDTO) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Pharmacist pharmacist = pharmacistMapper.bean2Entity(pharmacistDTO);
        WorkingHoursPharmacist workingHoursPharmacist = workingHoursPharmacistMapper.bean2Entity(pharmacistDTO.getWorkingHours());
        pharmacist.setPharmacy(pharmacy);
        workingHoursPharmacist.setPharmacist(pharmacist);
        workingHoursPharmacist.setPharmacy(pharmacy);
        pharmacist.setEnabled(false);

        pharmacist.setPassword(passwordEncoder.encode(pharmacist.getFirstName() + "2021"));
        List<Authority> auth = authorityService.findByName("ROLE_PHARMACIST");
        pharmacist.setAuthorities(auth);
        pharmacistRepository.save(pharmacist);
        Set<Pharmacist> pharmacistSet = pharmacy.getPharmacists();
        pharmacistSet.add(pharmacist);
        pharmacy.setPharmacists(pharmacistSet);
        pharmacyRepository.save(pharmacy);
        workingHoursPharmacistRepository.save(workingHoursPharmacist);
    }



    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            return pharmacyAdminOptional.get();
        }
        throw new ResourceConflictException(1L,"Ne postoji administrator apoteke!");
    }

    public Collection<Pharmacist> getAvailablePharmacist(Long id, String date, String time) throws ParseException {
        Collection<Pharmacist> freePharmacist = new HashSet<>();
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(id);
        Pharmacy ph  = pharmacy.get();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateParse = formatter.parse(date);
        int dayOfWeekInt = dateParse.getDay();
        int hours = Integer.parseInt(time.substring(0,2));
        int minutes = Integer.parseInt(time.substring(3));
        dateParse.setHours(hours);
        dateParse.setMinutes(minutes);
        System.out.println(dateParse);

        Collection<WorkingHoursPharmacist> workingHoursPharmacists = workingHoursPharmacistRepository.findByWorkDayAndPharmacy(dayOfWeek(dayOfWeekInt), ph);
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
            Collection<Counseling> scheduledCounseling = counselingRepository.findAllByDateAndPharmacistAndFree(dateParse, wk.getPharmacist(), false);
            System.out.println(dateParse);
            System.out.println(wk.getPharmacist());
            if (scheduledCounseling.size() != 0) {continue;}

            if (wk.getStartTime().before(help) && wk.getEndTime().after(help)) {
                freePharmacist.add(wk.getPharmacist());
            }
        }







        return freePharmacist;
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
}
