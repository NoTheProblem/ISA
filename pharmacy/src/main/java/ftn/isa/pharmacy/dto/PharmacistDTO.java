package ftn.isa.pharmacy.dto;

public class PharmacistDTO {

    private Long id;
    private float evaluationGrade;
    private String firstName;
    private String lastName;
    private String type;

    public PharmacistDTO() {
    }

    public PharmacistDTO(Long id, float evaluationGrade, String firstName, String lastName, String type) {
        this.id = id;
        this.evaluationGrade = evaluationGrade;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}