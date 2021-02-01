package ftn.isa.pharmacy.repository;


import ftn.isa.pharmacy.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {
}
