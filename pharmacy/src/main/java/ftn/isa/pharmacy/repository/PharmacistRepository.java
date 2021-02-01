package ftn.isa.pharmacy.repository;


import ftn.isa.pharmacy.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
}
