package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
