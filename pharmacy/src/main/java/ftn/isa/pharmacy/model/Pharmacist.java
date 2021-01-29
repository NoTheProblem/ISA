package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("pha")
public class Pharmacist extends User {

    @Column
    private float evaluationGrade;

    @Column(nullable = false)
    private Time beginning;

    @Column(nullable = false)
    private Time end;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Counseling> counselings = new HashSet<Counseling>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

}
