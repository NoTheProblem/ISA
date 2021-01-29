package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class LoyaltyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int downScore;

    @Column
    private int upScore;

    @Column
    private float discountPercentage;

    @Column
    private String typeOfLoyalty;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Patient> patients = new HashSet<Patient>();

}
