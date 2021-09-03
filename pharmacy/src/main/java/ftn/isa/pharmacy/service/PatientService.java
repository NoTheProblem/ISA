package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.*;
import ftn.isa.pharmacy.model.*;

import java.util.Collection;
import java.util.List;

public interface PatientService {

    void addAllergy(MedicineDto medicineDto);

    Collection<Patient> getAll();

    Collection<Medicine> getAllAllergyForPatient(String username);

    LoyaltyProgram getLoyaltyProgramForPatient(String username);

    void addExamination(ExaminationDto examinationDto);
    boolean cancelExamination(ExaminationDto examinationDto);

    void addCounseling(CounselingDTO counselingDto);

    boolean cancelCounseling(CounselingDTO counselingDto);

    void addReservation(ReservationDTO reservationDTO);

    boolean cancelReservation(ReservationDTO reservationDto);

    void addGrade(EvaluationDTO evaluationDTO);

    List<Evaluation> getAllHistoryEvaluation();

    void changeEvaluation(EvaluationDTO evaluationDTO);

    void Scheduler();

    void PenaltyScheduler();

    Patient getPatient();

    Collection<EPrescription> getAllRecepts();
}
