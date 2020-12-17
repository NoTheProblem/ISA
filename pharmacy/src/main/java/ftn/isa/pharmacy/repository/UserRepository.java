package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Sysadmin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<Sysadmin, Long> {

    List<Sysadmin> findByFirstNameAndLastNameAllIgnoringCase(String firstName, String lastName);
    List<Sysadmin> findAllByLastName(String lastName);
    List<Sysadmin> findAllByFirstName(String firsName);

}
