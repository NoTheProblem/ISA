package ftn.isa.pharmacy.model;


import javax.persistence.*;

@Entity
@DiscriminatorValue("pha")
public class Pharmacist extends User {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "pharmacy_pharmacist", joinColumns = @JoinColumn(name = "pharmacist_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"))
    private Pharmacy pharmacy;

}
