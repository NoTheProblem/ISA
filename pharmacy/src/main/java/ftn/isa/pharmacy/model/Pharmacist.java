package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("pha")
public class Pharmacist extends User {

    @Column
    private float evaluationGrade;

    @Column(nullable = false)
    private Time beginning;

    @Column(nullable = false)
    private Time end;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Counseling> counselings = new HashSet<Counseling>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    public Pharmacist() {
    }

    public Pharmacist(float evaluationGrade, Time beginning, Time end, Set<Counseling> counselings, Pharmacy pharmacy) {
        this.evaluationGrade = evaluationGrade;
        this.beginning = beginning;
        this.end = end;
        this.counselings = counselings;
        this.pharmacy = pharmacy;
    }

    public float getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(float evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public Time getBeginning() {
        return beginning;
    }

    public void setBeginning(Time beginning) {
        this.beginning = beginning;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
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
