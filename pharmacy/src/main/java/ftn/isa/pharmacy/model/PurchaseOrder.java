package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date endDate;

    @Column
    private String status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Bid> bids = new HashSet<Bid>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PharmacyAdmin pharmacyAdmin;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityOrder> orderMedicines = new HashSet<MedicineQuantityOrder>();


}
