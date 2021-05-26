package ftn.isa.pharmacy.repository;


import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    List<Examination> findAllByIsFree(Boolean isFree);
    List<Examination> findAllByDermatologist(Dermatologist dermatologist);
    List<Examination> findAllByDateBetween(Date startDate, Date endDate);

}
