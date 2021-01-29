package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name ="pharmacy")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column
    private String pharmacyDescription;

    @Column
    private float evaluationGrade;

    @Column
    private float counselingPrice;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityPharmacy> medicationQuantities = new HashSet<MedicineQuantityPharmacy>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();

    @ManyToMany
    @JoinTable(name = "pharmacy_dermatologist", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id"))
    private Set<Dermatologist> dermatologists = new HashSet<Dermatologist>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PriceMediceList> priceMediceLists = new HashSet<PriceMediceList>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PriceExaminationList> priceExaminationLists = new HashSet<PriceExaminationList>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkingHours> workingHours = new HashSet<WorkingHours>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Promotion> promotions = new HashSet<Promotion>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PharmacyAdmin> pharmacyAdmins = new HashSet<PharmacyAdmin>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> listOfFreeExamination = new HashSet<Examination>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<Reservation>();

    @ManyToMany
    @JoinTable(name = "pharmaci_missmedicine", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    private Set<Medicine> missingMedicine = new HashSet<Medicine>();

}
