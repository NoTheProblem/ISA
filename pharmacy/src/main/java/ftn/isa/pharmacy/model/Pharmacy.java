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

    @Column()
    private float averageRating;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();

    @ManyToMany
    @JoinTable(name = "pharmacy_medicine", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    private Set<Medicine> medicines = new HashSet<Medicine>();

    @ManyToMany
    @JoinTable(name = "pharmacy_dermatologist", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id"))
    private Set<Dermatologist> dermatologists = new HashSet<Dermatologist>();


    public Pharmacy() {
    }

    public Pharmacy(Long id, String name, String country, String city, String address, float averageRating, Set<Pharmacist> pharmacists, Set<Dermatologist> dermatologists) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.averageRating = averageRating;
        this.pharmacists = pharmacists;
        this.dermatologists = dermatologists;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public Set<Pharmacist> getPharmacists() {
        return pharmacists;
    }

    public Set<Dermatologist> getDermatologists() {
        return dermatologists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public void setPharmacists(Set<Pharmacist> pharmacists) {
        this.pharmacists = pharmacists;
    }

    public void setDermatologists(Set<Dermatologist> dermatologists) {
        this.dermatologists = dermatologists;
    }
}
