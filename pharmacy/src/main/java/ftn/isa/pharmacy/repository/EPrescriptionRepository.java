package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.EPrescription;
import ftn.isa.pharmacy.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EPrescriptionRepository extends JpaRepository<EPrescription, Long> {
    Collection<EPrescription> findAllByPatient(Patient patient);
}
