package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.model.LoyaltyProgram;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.Patient;

import java.util.Collection;

public interface PatientService {

    void addAllergy(MedicineDto medicineDto);

    Collection<Patient> getAll();

    Collection<Medicine> getAllAllergyForPatient(String username);

    LoyaltyProgram getLoyaltyProgramForPatient(String username);

    void addExamination(ExaminationDto examinationDto);
    void cancelExamination(ExaminationDto examinationDto);
}
