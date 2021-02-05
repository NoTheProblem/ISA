package ftn.isa.pharmacy.dto;

import java.sql.Time;
import java.util.Date;

public class DermatologistDto {

    private Long id;

    private float evaluationGrade;

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



}
