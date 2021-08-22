package ftn.isa.pharmacy.model;

import javax.persistence.*;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @Column
    private Long idOfEvaluated;

    @Column
    private String typeOfEvaluation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    private String name;

    public int getNumberOfEvaluation() {
        return numberOfEvaluation;
    }

    public void setNumberOfEvaluation(int numberOfEvaluation) {
        this.numberOfEvaluation = numberOfEvaluation;
    }

    @Column
    private int numberOfEvaluation;

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Column
    private float grade;

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Column
    private Boolean valid;


    public Evaluation() {
    }

    public Evaluation(Long id, Patient patient, Long idOfEvaluated, String typeOfEvaluation, int numberOfEvaluation, float grade, Boolean valid, String name) {
        this.id = id;
        this.patient = patient;
        this.idOfEvaluated = idOfEvaluated;
        this.typeOfEvaluation = typeOfEvaluation;
        this.numberOfEvaluation  = numberOfEvaluation;
        this.grade = grade;
        this.valid = valid;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getIdOfEvaluated() {
        return idOfEvaluated;
    }

    public void setIdOfEvaluated(Long idOfEvaluated) {
        this.idOfEvaluated = idOfEvaluated;
    }

    public String getTypeOfEvaluation() {
        return typeOfEvaluation;
    }

    public void setTypeOfEvaluation(String typeOfEvaluation) {
        this.typeOfEvaluation = typeOfEvaluation;
    }
}
