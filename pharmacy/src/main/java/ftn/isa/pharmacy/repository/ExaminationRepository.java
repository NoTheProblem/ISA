package ftn.isa.pharmacy.repository;


import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Examination;
import ftn.isa.pharmacy.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    List<Examination> findAllByPharmacyAndIsFree(Pharmacy pharmacy, Boolean isFree);
    List<Examination> findAllByDermatologist(Dermatologist dermatologist);
    List<Examination> findAllByDateBetween(Date startDate, Date endDate);
    List<Examination> findAllByPharmacyAndDateBetweenAndIsFree(Pharmacy pharmacy, Date startDate, Date endDate, Boolean isFree);
    List<Examination> findAllByPharmacyAndDateBetween(Pharmacy pharmacy, Date startDate, Date endDate);
    List<Examination> findAllByDateBetweenAndPharmacy(Date startDate, Date endDate, Pharmacy pharmacy);
    List<Examination> findAllByPharmacyAndDateBeforeAndDateAfter(Pharmacy pharmacy, Date startDate, Date endDate);


    @Query(value = "select * from examination where pharmacy_id = ?1 and dermatologist_id = ?2 and Date(date) = Date(?3)",
            nativeQuery = true)
    List<Examination> customByPharmacyDermatologistAndDate(Long pharmacyId, Long dermatologistId, String date);
    List<Examination> findAllByPharmacyAndDermatologistAndDate(Pharmacy pharmacy, Dermatologist dermatologist, Date date);


    @Query(value = "select * from examination where pharmacy_id = ?1 and date  between ?2 and ?3",
            nativeQuery = true)
    List<Examination> getForReport(Long id, String start, String end);

    @Query(value = "select * from examination where patient_id = ?1 and date >= current_date ",
            nativeQuery = true)
    List<Examination> customByPatientIdAndDate(Long id);

    @Query(value = "select * from examination where patient_id = ?1 and date <= current_date and penalty=false",
            nativeQuery = true)
    Collection<Examination> customByPatientIdAndDateAndPenalty(Long id);

    Collection<Examination> findAllByDateBetweenAndPenalty(Date day_begin, Date day_end, boolean b);

    @Query(value = "select * from examination where is_free=true",
            nativeQuery = true)
    List<Examination> findAllFree(Boolean free, LockModeType pessimisticRead);
}

