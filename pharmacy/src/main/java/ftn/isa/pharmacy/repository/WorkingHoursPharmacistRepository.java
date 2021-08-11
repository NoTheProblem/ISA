package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.model.WorkingHoursPharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface WorkingHoursPharmacistRepository  extends JpaRepository<WorkingHoursPharmacist, Long> {

    Collection<WorkingHoursPharmacist> findByWorkDay(String workDay);

    Collection<WorkingHoursPharmacist> findByWorkDayAndPharmacy(String dayOfWeek, Pharmacy pharmacy);
}
