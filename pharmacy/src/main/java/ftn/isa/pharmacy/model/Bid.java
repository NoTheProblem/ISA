package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float price;

    @Column
    private String status;

    @Column
    private Date endDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Supplier supplier;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PurchaseOrder purchaseOrder;

}
