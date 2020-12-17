package ftn.isa.pharmacy.model;


import javax.persistence.*;

@Entity
@DiscriminatorValue("phaadmin")
public class PharmacyAdmin extends User {

}
