package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.MedicineQuantityOrder;
import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineQuantityOrderRepository extends JpaRepository<MedicineQuantityOrder, Long> {


}
