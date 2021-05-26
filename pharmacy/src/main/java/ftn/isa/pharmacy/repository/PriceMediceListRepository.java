package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.model.PriceMediceList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PriceMediceListRepository extends JpaRepository<PriceMediceList, Long> {

    List<PriceMediceList> findAllByPharmacyAndMedicineOrderByEndDateDesc(Pharmacy pharmacy, Medicine medicine);

    List<PriceMediceList> findAllByPharmacyAndMedicineAndStartDateAfterOrEndDateAfter(Pharmacy pharmacy, Medicine medicine, Date startDatem, Date endDate);
}
