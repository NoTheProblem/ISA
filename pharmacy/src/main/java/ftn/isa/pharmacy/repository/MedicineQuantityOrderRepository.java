package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.MedicineQuantityOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineQuantityOrderRepository extends JpaRepository<MedicineQuantityOrder, Long> {
}
