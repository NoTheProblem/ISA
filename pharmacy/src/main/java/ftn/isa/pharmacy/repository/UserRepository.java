package ftn.isa.pharmacy.repository;


import ftn.isa.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {


}
