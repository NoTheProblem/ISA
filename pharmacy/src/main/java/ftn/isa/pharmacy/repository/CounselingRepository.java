package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Counseling;
import ftn.isa.pharmacy.model.Examination;
import ftn.isa.pharmacy.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {
    Collection<Counseling> findAllByDate(Date dateParse);

    Collection<Counseling> findAllByDateAndPharmacist(Date dateParse, Pharmacist pharmacist);

    @Query(value = "select * from counseling where patient_id = ?1 and date >= current_date ",
            nativeQuery = true)
    List<Counseling> customByPatientIdAndDate(Long id);

    Collection<Counseling> findAllByDateAndPharmacistAndFree(Date dateParse, Pharmacist pharmacist, boolean b);
}
