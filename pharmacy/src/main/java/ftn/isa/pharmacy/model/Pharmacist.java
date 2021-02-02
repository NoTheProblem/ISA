package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_PHARMACIST")
public class Pharmacist extends User {

    @Column
    private float evaluationGrade;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Counseling> counselings = new HashSet<Counseling>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    public Pharmacist() {
    }

    public Pharmacist(float evaluationGrade, Time startTime, Time endTime, Set<Counseling> counselings, Pharmacy pharmacy) {
        this.evaluationGrade = evaluationGrade;
        this.startTime = startTime;
        this.endTime = endTime;
        this.counselings = counselings;
        this.pharmacy = pharmacy;
    }

    public float getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(float evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time beginning) {
        this.startTime = beginning;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time end) {
        this.endTime = end;
    }

    public Set<Counseling> getCounselings() {
        return counselings;
    }

    public void setCounselings(Set<Counseling> counselings) {
        this.counselings = counselings;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
