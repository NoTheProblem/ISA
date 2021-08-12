package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.model.Counseling;
import ftn.isa.pharmacy.model.Reservation;

import java.util.Collection;

public interface ReservationService {

    Collection<Reservation> getAllReservedMedicineForPatient();

}
