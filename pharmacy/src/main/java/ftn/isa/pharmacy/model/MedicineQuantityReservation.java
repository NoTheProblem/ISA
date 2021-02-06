package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.Optional;

@Entity
public class MedicineQuantityReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;



    @Override
    public String toString() {
        return "MedicineQuantityReservation{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", medicine=" + medicine +
                ", reservation=" + reservation +
                '}';
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Medicine medicine;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Reservation reservation;

    public MedicineQuantityReservation() {
    }

    public MedicineQuantityReservation(Long id, int quantity, Medicine medicine, Reservation reservation) {
        this.id = id;
        this.quantity = quantity;
        this.medicine = medicine;
        this.reservation = reservation;
    }


    public MedicineQuantityReservation(int quantity, Reservation reservation, Medicine medicine) {
        this.quantity = quantity;
        this.medicine = medicine;
        this.reservation = reservation;
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

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
