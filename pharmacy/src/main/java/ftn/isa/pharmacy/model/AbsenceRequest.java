package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class AbsenceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @Column
    private String typeOfAbsence;

    @Column
    private String requstText;

    @Column
    private String answerText;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private Long employeeId;

    @Column
    private Long adminId;

    public AbsenceRequest() {
    }

    public AbsenceRequest(Long id, String status, String typeOfAbsence, String requstText, String answerText, Date startDate, Date endDate) {
        this.id = id;
        this.status = status;
        this.typeOfAbsence = typeOfAbsence;
        this.requstText = requstText;
        this.answerText = answerText;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AbsenceRequest(AbsenceRequest a){
        this.id = a.id;
        this.status = a.status;
        this.typeOfAbsence = a.typeOfAbsence;
        this.requstText = a.requstText;
        this.answerText = a.answerText;
        this.startDate = a.startDate;
        this.endDate = a.endDate;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getTypeOfAbsence() {
        return typeOfAbsence;
    }

    public String getRequstText() {
        return requstText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTypeOfAbsence(String typeOfAbsence) {
        this.typeOfAbsence = typeOfAbsence;
    }

    public void setRequstText(String requstText) {
        this.requstText = requstText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
