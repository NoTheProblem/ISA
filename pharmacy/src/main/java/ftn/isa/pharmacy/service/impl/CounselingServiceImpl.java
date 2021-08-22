package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.CounselingRepository;
import ftn.isa.pharmacy.repository.EvaluationRepository;
import ftn.isa.pharmacy.repository.PatientRepository;
import ftn.isa.pharmacy.service.CounselingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CounselingServiceImpl implements CounselingService {

    private final CounselingRepository counselingRepository;
    private final PatientRepository patientRepository;
    private final EvaluationRepository evaluationRepository;


    @Autowired
    public CounselingServiceImpl(EvaluationRepository evaluationRepository, PatientRepository patientRepository, CounselingRepository counselingRepository) {
        this.counselingRepository = counselingRepository;
        this.patientRepository = patientRepository;
        this.evaluationRepository = evaluationRepository;
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

    @Override
    public Collection<Pharmacist> getAllHistoryPhaForEvaluation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        System.out.println(((User) authentication.getPrincipal()).getId());
        Patient patient = patientOptional.get();
        Collection<Pharmacist> pharmacists= new HashSet<>();
        Collection<Counseling> counselings = counselingRepository.customByPatientIdAndDateAndPenalty(patient.getId());
        for (Counseling counseling: counselings) {
            Pharmacist pharmacist = counseling.getPharmacist();
            System.out.println(pharmacist);
            List<Evaluation> evaluations  = evaluationRepository.findAllByIdOfEvaluatedAndPatientAndTypeOfEvaluation(pharmacist.getId(), patient, "farmaceut");
            if (evaluations.size() == 0){
                pharmacists.add(pharmacist);
            }
        }

        Collection<Pharmacist> pharmacistUnique = new Stack<>();
        for (Pharmacist pharmacist : pharmacists) {
            if (!pharmacistUnique.contains(pharmacist)){
                pharmacistUnique.add(pharmacist);
            }

        }

        return pharmacistUnique;



    }
}

