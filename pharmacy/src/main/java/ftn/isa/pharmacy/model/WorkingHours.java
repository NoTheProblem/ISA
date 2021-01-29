package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.sql.Time;

@Entity
public class WorkingHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Time beginning;

    @Column
    private Time end;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Dermatologist dermatologist;

}
