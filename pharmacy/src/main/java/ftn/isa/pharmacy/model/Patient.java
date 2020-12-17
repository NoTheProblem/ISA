package ftn.isa.pharmacy.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("pat")
public class Patient extends User{
}
