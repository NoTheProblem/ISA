package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.mapper.impl.ExaminationMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.DermatologistRepository;
import ftn.isa.pharmacy.repository.ExaminationRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.WorkingHoursRepository;
import ftn.isa.pharmacy.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationRepository examinationRepository;
    private final ExaminationMapperImpl examinationMapper;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final DermatologistRepository dermatologistRepository;
    private final WorkingHoursRepository workingHoursRepository;


    @Autowired
    public ExaminationServiceImpl(ExaminationRepository examinationRepository, ExaminationMapperImpl examinationMapper, PharmacyAdminRepository pharmacyAdminRepository, DermatologistRepository dermatologistRepository, WorkingHoursRepository workingHoursRepository) {
        this.examinationRepository = examinationRepository;
        this.examinationMapper = examinationMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.workingHoursRepository = workingHoursRepository;
    }

    @Override
    public List<Examination> getAllFree(Boolean free) {
        return examinationRepository.findAllByIsFree(free);
    }

    @Override
    public boolean addExamination(ExaminationDto examinationDto) {
        String time = examinationDto.getTime();
        Dermatologist dermatologist = dermatologistRepository.getOne(examinationDto.getDermatologistId());
        Examination examination = examinationMapper.bean2Entity(examinationDto);
        Date date = examination.getDate();
        int hours = Integer.valueOf(time.substring(0,2));
        date.setHours(hours);
        int minutes = Integer.valueOf(time.substring(3));
        date.setMinutes(minutes);
        examination.setDate(date);
        Date endDate = new Date();
        endDate.setTime(date.getTime());
        endDate.setMinutes(date.getMinutes()+examination.getDurationMinutes());
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        WorkingHours wk = workingHoursRepository.getWorkingHoursByPharmacyAndDermatologist(pharmacy,dermatologist);
        Time startShift = wk.getStartTime();
        Time endShift = wk.getEndTime();
        Date dateStartShift = new Date();
        dateStartShift.setTime(date.getTime());
        dateStartShift.setMinutes(startShift.getMinutes());
        dateStartShift.setHours(startShift.getHours());
        Date dateEndShift = new Date();
        dateEndShift.setTime(date.getTime());
        dateEndShift.setMinutes(endShift.getMinutes());
        dateEndShift.setHours(endShift.getHours());
        if(date.after(dateEndShift) || endDate.before(dateStartShift)){
            return false;
        }
        Date startDate = new Date();
        startDate.setTime(date.getTime());
        startDate.setHours(1);
        Date nextDay =  new Date();
        nextDay.setTime(date.getTime());
        nextDay.setHours(23);
        //TODO proveriti je l ok radno vreme dermatologa
        List<Examination> examinationsOnThatDay = examinationRepository.findAllByDateBetween(startDate,nextDay);
        for (Examination exa: examinationsOnThatDay) {
            Date exaDate = exa.getDate();
            Date exaStart =  new Date();
            exaStart.setTime(exaDate.getTime());
            Date exaEnd =  new Date();
            exaEnd.setTime(exaDate.getTime());
            exaEnd.setMinutes(exa.getDate().getHours()+ exa.getDurationMinutes());
            if (date.after(exaStart) && date.before(exaEnd)){
                return false;
            }
            if(endDate.after(exaStart) && endDate.before(exaEnd)){
                return false;
            }
        }
        examination.setPharmacy(pharmacy);
        examination.setDermatologist(dermatologist);
        examination.setFree(true);
        examinationRepository.save(examination);
        return  true;
    }




    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            return pharmacyAdminOptional.get();
        }
        return null;
    }
}
