package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {


    @Query("select u from LoyaltyProgram u where u.downScore <= ?1 and u.upScore >= ?1")
    LoyaltyProgram findByLoyaltyScore(@Param("loyaltyScore") int loyaltyScore);
}
