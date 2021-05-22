package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.sql.Time;

@Entity
public class WorkingHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Dermatologist dermatologist;

    public WorkingHours() {
    }

    public WorkingHours(Long id, Time startTime, Time endTime, Pharmacy pharmacy, Dermatologist dermatologist) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pharmacy = pharmacy;
        this.dermatologist = dermatologist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time beginning) {
        this.startTime = beginning;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time end) {
        this.endTime = end;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }


}
