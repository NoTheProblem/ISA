package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findAllByCode(String code);
}
