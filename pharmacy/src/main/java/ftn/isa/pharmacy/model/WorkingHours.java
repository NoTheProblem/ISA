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

    public WorkingHours() {
    }

    public WorkingHours(Long id, Time beginning, Time end, Pharmacy pharmacy, Dermatologist dermatologist) {
        this.id = id;
        this.beginning = beginning;
        this.end = end;
        this.pharmacy = pharmacy;
        this.dermatologist = dermatologist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getBeginning() {
        return beginning;
    }

    public void setBeginning(Time beginning) {
        this.beginning = beginning;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
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
