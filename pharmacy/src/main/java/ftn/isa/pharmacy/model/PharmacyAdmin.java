package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("phaadmin")
public class PharmacyAdmin extends User {



    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<PurchaseOrder>();

}
