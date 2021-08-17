package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.model.Counseling;

import java.util.Collection;
import java.util.List;

public interface CounselingService {

    List<Counseling> getAll();

    Collection<Counseling> getAllScheduledAppointmentForPatient();

    Collection<Counseling> getAllHistoryPha();
}


