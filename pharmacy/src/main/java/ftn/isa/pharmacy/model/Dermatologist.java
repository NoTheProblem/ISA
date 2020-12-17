package ftn.isa.pharmacy.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("derma")
public class Dermatologist extends User{


    @ManyToMany(mappedBy = "dermatologists")
    private Set<Pharmacy> pharmacys = new HashSet<Pharmacy>();
}
