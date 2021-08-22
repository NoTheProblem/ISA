package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Evaluation;
import ftn.isa.pharmacy.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    List<Evaluation> findAllByIdOfEvaluatedAndPatient(Long id, Patient patient);

    List<Evaluation> findByIdOfEvaluated(Long idOfEvaluated);

    List<Evaluation> findByIdOfEvaluatedAndValid(Long idOfEvaluated, boolean b);

    List<Evaluation> findAllByIdOfEvaluatedAndPatientAndTypeOfEvaluation(Long id, Patient patient, String tip);

    Set<Evaluation> findByIdOfEvaluatedAndValidAndTypeOfEvaluation(Long idOfEvaluated, boolean b, String tip);

    List<Evaluation> findAllByPatient(Patient patient);


    List<Evaluation> findAllByIdOfEvaluatedAndTypeOfEvaluation(Long idOfEvaluated, String type);
}
