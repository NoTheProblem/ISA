package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.MedicineQuantityReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineQuantityReservationRepository extends JpaRepository<MedicineQuantityReservation, Long> {
}
