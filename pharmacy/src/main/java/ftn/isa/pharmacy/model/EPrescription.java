package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Entity
public class EPrescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private float price;

    @Column
    private String status;

    @Column
    private Date dateOfIssue;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PersList> medicines = new HashSet<PersList>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

}
