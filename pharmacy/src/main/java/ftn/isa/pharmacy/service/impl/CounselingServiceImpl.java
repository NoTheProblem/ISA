package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.Counseling;
import ftn.isa.pharmacy.model.Examination;
import ftn.isa.pharmacy.model.Patient;
import ftn.isa.pharmacy.model.User;
import ftn.isa.pharmacy.repository.CounselingRepository;
import ftn.isa.pharmacy.repository.PatientRepository;
import ftn.isa.pharmacy.service.CounselingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CounselingServiceImpl implements CounselingService {

    private final CounselingRepository counselingRepository;
    private final PatientRepository patientRepository;


    @Autowired
    public CounselingServiceImpl(PatientRepository patientRepository, CounselingRepository counselingRepository) {
        this.counselingRepository = counselingRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Counseling> getAll() {
        return counselingRepository.findAll();
    }

    @Override
    public Collection<Counseling> getAllScheduledAppointmentForPatient(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        System.out.println(((User) authentication.getPrincipal()).getId());
        Patient patient = patientOptional.get();
        return counselingRepository.customByPatientIdAndDate(patient.getId());

    }

    @Override
    public Collection<Counseling> getAllHistoryPha(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        System.out.println(((User) authentication.getPrincipal()).getId());
        Patient patient = patientOptional.get();
        return counselingRepository.customByPatientIdAndDateAndPenalty(patient.getId());

    }
}

