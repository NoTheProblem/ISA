package ftn.isa.pharmacy.dto;

import java.sql.Time;
import java.util.Date;

public class PharmacistDTO {

    private Long id;
    private float evaluationGrade;
    private String name;
    private String lastname;
    private String type;

    public PharmacistDTO() {
    }

    public PharmacistDTO(Long id, float evaluationGrade, String name, String lastname, String type) {
        this.id = id;
        this.evaluationGrade = evaluationGrade;
        this.name = name;
        this.lastname = lastname;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(float evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
