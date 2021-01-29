package ftn.isa.pharmacy.model;

import javax.persistence.*;


@Entity
public class PersList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @Column
    private String wayOfUse;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Medicine medicine;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EPrescription ePrescription;

    public PersList() {
    }

    public PersList(Long id, int quantity, String wayOfUse, Medicine medicine, EPrescription ePrescription) {
        this.id = id;
        this.quantity = quantity;
        this.wayOfUse = wayOfUse;
        this.medicine = medicine;
        this.ePrescription = ePrescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getWayOfUse() {
        return wayOfUse;
    }

    public void setWayOfUse(String wayOfUse) {
        this.wayOfUse = wayOfUse;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public EPrescription getePrescription() {
        return ePrescription;
    }

    public void setePrescription(EPrescription ePrescription) {
        this.ePrescription = ePrescription;
    }
}
