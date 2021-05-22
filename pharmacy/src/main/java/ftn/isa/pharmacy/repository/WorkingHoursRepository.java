package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.AbsenceRequest;
import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.model.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {

    WorkingHours getWorkingHoursByPharmacyAndDermatologist(Pharmacy pharmacy, Dermatologist dermatologist);
}


