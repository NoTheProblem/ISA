package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column
    private String shape;

    @Column(nullable = false)
    private String composition;

    @Column
    private String manufacturer;

    @Column
    private Boolean prescriptionRequirement;

    @Column
    private String additionalNotes;

    @Column
    private float evaluationGrade;

    @Column
    private int loyaltyScore;

    @Column(nullable = false)
    private String contraindications;

    //recommended daily intake of therapy
    @Column(nullable = false)
    private String rdiot;

    @ManyToMany(mappedBy = "basedMedicines")
    private Set<Medicine> replacedMedicines = new HashSet<Medicine>();

    @ManyToMany
    @JoinTable(name = "basedMedicines", joinColumns = @JoinColumn(name = "basedMedicine", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "replacedMedicine", referencedColumnName = "id"))
    private Set<Medicine> basedMedicines = new HashSet<Medicine>();




    @ManyToMany(mappedBy = "allergicMedicines")
    private Set<Patient> alergicMedicine = new HashSet<Patient>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PriceMediceList> priceMediceLists = new HashSet<PriceMediceList>();

    @ManyToMany(mappedBy = "missingMedicine")
    private Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityReservation> reservationMedicines = new HashSet<MedicineQuantityReservation>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityOrder> orderMedicines = new HashSet<MedicineQuantityOrder>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityPharmacy> medicationQuantities = new HashSet<MedicineQuantityPharmacy>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantitySupplier> suppliersMedicines = new HashSet<MedicineQuantitySupplier>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PersList> persLists = new HashSet<PersList>();


}
