package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Counseling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean free;

    @Column
    private Boolean penalty;

    @Column
    private String counselingReport;

    @Column
    private int loyaltyScore;

    @Column
    private Date date;

    @Column
    private Time time;

    @Column
    private float price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacist pharmacist;
}
