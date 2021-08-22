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

    @OneToOne(cascade = CascadeType.ALL)
    private Medicine medicine;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Pharmacy pharmacy;

    public EPrescription() {
    }

    public EPrescription(Long id, String code, float price, String status, Date dateOfIssue, Medicine medicines, Patient patient, Pharmacy pharmacy) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.status = status;
        this.dateOfIssue = dateOfIssue;
        this.medicine = medicines;
        this.patient = patient;
        this.pharmacy = pharmacy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }



    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
