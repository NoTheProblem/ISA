package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "pricelist")
public class PriceMediceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private float price;

    @Column
    private String currency;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Medicine medicine;


}
