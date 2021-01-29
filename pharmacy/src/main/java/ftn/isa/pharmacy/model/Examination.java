package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean penalty;

    @Column
    private Boolean isFree;

    @Column
    private String examinationReport;

    @Column
    private int loyaltyScore;

    @Column
    private Date date;

    @Column
    private Time time;

    @Column
    private int durationMinutes;

    @Column
    private float price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Dermatologist dermatologist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;
}
