package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.SysAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<SysAdmin, Long> {

    List<SysAdmin> findByFirstNameAndLastNameAllIgnoringCase(String firstName, String lastName);
    List<SysAdmin> findAllByLastName(String lastName);
    List<SysAdmin> findAllByFirstName(String firsName);

}
