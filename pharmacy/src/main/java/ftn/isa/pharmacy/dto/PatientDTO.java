package ftn.isa.pharmacy.dto;

public class PatientDTO {


    private Long id;

    private String firstName;
    private String lastName;

    public int getPenalty() {
        return penalty_score;
    }

    public void setPenalty(int penalty_score) {
        this.penalty_score = penalty_score;
    }

    private int penalty_score;

    public PatientDTO(Long id, String firstName, String lastName, int penalty_score) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.penalty_score = penalty_score;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PatientDTO() {
    }
}
