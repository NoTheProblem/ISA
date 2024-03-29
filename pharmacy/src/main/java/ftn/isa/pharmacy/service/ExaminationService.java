package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Examination;

import java.util.Collection;
import java.util.List;

public interface ExaminationService {

    public List<Examination> getAllFree(Boolean free);


    boolean addExamination(ExaminationDto examinationDto);

    Collection<Examination> getByDermaIdAndDateForPhaAdmin(Long id, String date);

    Collection<Examination> getAllScheduledAppointmentForPatient();

    Collection<Examination> getAllHistoryDerma();

    Collection<Dermatologist> getAllHistoryDermaForEvaluation();
}
