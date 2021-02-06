package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.ReservationDto;
import ftn.isa.pharmacy.model.Reservation;

public interface PatientService {

    void addAllergy(MedicineDto medicineDto);
    void addReservation(ReservationDto reservationDtoDto);

}
