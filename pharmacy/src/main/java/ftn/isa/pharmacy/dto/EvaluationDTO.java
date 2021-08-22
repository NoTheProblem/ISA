package ftn.isa.pharmacy.dto;

public class EvaluationDTO {

    private Long id;

    public EvaluationDTO(Long id, Long idOfEvaluated, String typeOfEvaluation, int numberOfEvaluation, float grade, Boolean valid, String name) {
        this.id = id;
        this.idOfEvaluated = idOfEvaluated;
        this.typeOfEvaluation = typeOfEvaluation;
        this.numberOfEvaluation = numberOfEvaluation;
        this.grade = grade;
        this.valid = valid;
        this.name = name;
    }

    private Long idOfEvaluated;

    public EvaluationDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getNumberOfEvaluation() {
        return numberOfEvaluation;
    }

    public void setNumberOfEvaluation(int numberOfEvaluation) {
        this.numberOfEvaluation = numberOfEvaluation;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    private String typeOfEvaluation;

    private int numberOfEvaluation;

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    private float grade;

    private Boolean valid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


}
