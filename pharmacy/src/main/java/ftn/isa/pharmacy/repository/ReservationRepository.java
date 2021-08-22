package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    List<Reservation> findAllByPharmacyAndPickUpDateBetweenAndPickedUpIsTrue(Pharmacy pharmacy, Date startDate, Date endDate);

    List<Reservation> getAllByPickedUpAndPatient(boolean b, Patient patient);

    Reservation findByPharmacyAndMedicineAndPatient(Pharmacy pharmacy, Medicine medicine, Patient patient);



    Collection<Reservation> findAllByPatientAndPickedUp(Patient patient, boolean b);
}
