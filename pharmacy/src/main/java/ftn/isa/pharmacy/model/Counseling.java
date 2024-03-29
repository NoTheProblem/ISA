package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Counseling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean free;

    @Override
    public String toString() {
        return "Counseling{" +
                "id=" + id +
                ", free=" + free +
                ", penalty=" + penalty +
                ", counselingReport='" + counselingReport + '\'' +
                ", loyaltyScore=" + loyaltyScore +
                ", date=" + date +
                ", time=" + time +
                ", price=" + price +
                ", patient=" + patient +
                ", pharmacist=" + pharmacist +
                '}';
    }

    @Column
    private Boolean penalty;

    @Column
    private String counselingReport;

    @Column
    private int loyaltyScore;

    @Column
    private Date date;

    @Column
    private String time;

    @Column
    private float price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacist pharmacist;

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Column
    private int durationMinutes;


    public Counseling() {
    }

    public Counseling(Long id, Boolean free, Boolean penalty, String counselingReport, int loyaltyScore, Date date, String time, float price, Patient patient, Pharmacist pharmacist, int durationMinutes) {
        this.id = id;
        this.free = free;
        this.penalty = penalty;
        this.counselingReport = counselingReport;
        this.loyaltyScore = loyaltyScore;
        this.date = date;
        this.time = time;
        this.price = price;
        this.patient = patient;
        this.pharmacist = pharmacist;
        this.durationMinutes = durationMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Boolean getPenalty() {
        return penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    public String getCounselingReport() {
        return counselingReport;
    }

    public void setCounselingReport(String counselingReport) {
        this.counselingReport = counselingReport;
    }

    public int getLoyaltyScore() {
        return loyaltyScore;
    }

    public void setLoyaltyScore(int loyaltyScore) {
        this.loyaltyScore = loyaltyScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
}
