package ftn.isa.pharmacy.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("supp")
public class Supplier extends User{
}
