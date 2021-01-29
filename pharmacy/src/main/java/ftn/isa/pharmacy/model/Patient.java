package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("pat")
public class Patient extends User{

    @Column
    private int loyaltyScore;

    @Column
    private int penaltyScore;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoyaltyProgram loyaltyProgram;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EPrescription> ePrescriptions = new HashSet<EPrescription>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Complaint> complaints = new HashSet<Complaint>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Counseling> counselings = new HashSet<Counseling>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<Examination>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<Reservation>();

    @ManyToMany
    @JoinTable(name = "allergicMedicines", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    private Set<Medicine> allergicMedicines = new HashSet<Medicine>();

    @ManyToMany
    @JoinTable(name = "patient_promotion", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "promotion_id", referencedColumnName = "id"))
    private Set<Promotion> subscribedPromotions = new HashSet<Promotion>();

}
