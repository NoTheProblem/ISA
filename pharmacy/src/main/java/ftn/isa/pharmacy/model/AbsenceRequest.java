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

    public AbsenceRequest(Long id, String status, String typeOfAbsence, String requstText, String answerText, Date startDate, Date endDate, Long employeeId, Long adminId) {
        this.id = id;
        this.status = status;
        this.typeOfAbsence = typeOfAbsence;
        this.requstText = requstText;
        this.answerText = answerText;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeId = employeeId;
        this.adminId = adminId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeOfAbsence() {
        return typeOfAbsence;
    }

    public void setTypeOfAbsence(String typeOfAbsence) {
        this.typeOfAbsence = typeOfAbsence;
    }

    public String getRequstText() {
        return requstText;
    }

    public void setRequstText(String requstText) {
        this.requstText = requstText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
