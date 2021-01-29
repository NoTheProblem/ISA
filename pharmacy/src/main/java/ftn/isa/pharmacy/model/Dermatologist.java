package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("derma")
public class Dermatologist extends User{

    @Column
    private String evaluationGrade;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkingHours> workingHours = new HashSet<WorkingHours>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<Examination>();

    @ManyToMany(mappedBy = "dermatologists")
    private Set<Pharmacy> pharmacys = new HashSet<Pharmacy>();

}
