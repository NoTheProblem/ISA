package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.AbsenceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface AbsenceRequestRepository extends JpaRepository<AbsenceRequest, Long> {

    @Query(value = "SELECT * FROM absence_request ar WHERE ar.type_of_employee = 'ROLE_DERMATOLOGIST' and ar.status = 'nov'",
    nativeQuery = true)
    Collection<AbsenceRequest> getAllDermatologistRequests();

    @Query(value = "SELECT * FROM absence_request ar WHERE ar.type_of_employee = 'ROLE_PHARMACIST' and ar.status = 'nov'",
            nativeQuery = true)
    Collection<AbsenceRequest> getAllPharmacistRequests();
}
