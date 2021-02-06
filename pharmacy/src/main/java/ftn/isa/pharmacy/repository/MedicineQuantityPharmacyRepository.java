package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineQuantityPharmacyRepository extends JpaRepository<MedicineQuantityPharmacy, Long> {

    List<MedicineQuantityPharmacy> findByPharmacyIdAndMedicineId(Long medicineId, Long PharmacyId);


    Optional<MedicineQuantityPharmacy> findByMedicineId(Long aLong);


}
