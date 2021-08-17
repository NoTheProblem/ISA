package ftn.isa.pharmacy.dto;

import java.util.Date;

public class CounselingDTO {



    private Long id;

    private Boolean penalty;

    private Boolean isFree;

    private String counselingReport;

    private int loyaltyScore;

    private Date date;

    private String time;

    private float price;

    private PharmacistDTO pharmacistDto;

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    private int durationMinutes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPenalty() {
        return penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public String getCounselingReport() {
        return counselingReport;
    }

    public void setCounselingReport(String counselingReport) {
        this.counselingReport = counselingReport;
    }

    public int getLoyaltyScore() {
        return loyaltyScore;
    }

    public void setLoyaltyScore(int loyaltyScore) {
        this.loyaltyScore = loyaltyScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public PharmacistDTO getPharmacistDto() {
        return pharmacistDto;
    }

    public void setPharmacistDto(PharmacistDTO pharmacistDto) {
        this.pharmacistDto = pharmacistDto;
    }



    public CounselingDTO() {
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    public void setPharmacistName(String pharmacistName) {
        this.pharmacistName = pharmacistName;
    }

    public String getPharmacistLastName() {
        return pharmacistLastName;
    }

    public void setPharmacistLastName(String pharmacistLastName) {
        this.pharmacistLastName = pharmacistLastName;
    }

    public Long getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(Long pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public Float getPharmacistEvaluationGrade() {
        return pharmacistEvaluationGrade;
    }

    public void setPharmacistEvaluationGrade(Float pharmacistEvaluationGrade) {
        this.pharmacistEvaluationGrade = pharmacistEvaluationGrade;
    }

    Long patientId;

    String pharmacistName;
    String pharmacistLastName;
    Long pharmacistId;
    Float pharmacistEvaluationGrade;

    public CounselingDTO(Long id, Boolean penalty, Boolean isFree, String counselingReport, int loyaltyScore, Date date, String time, float price, PharmacistDTO pharmacistDto, Long patientId, String pharmacistName, String pharmacistLastname, Long pharmacistId, Float pharmacistEvaluationGrade, int durationMinutes) {
        this.id = id;
        this.penalty = penalty;
        this.isFree = isFree;
        this.counselingReport = counselingReport;
        this.loyaltyScore = loyaltyScore;
        this.date = date;
        this.time = time;
        this.price = price;
        this.pharmacistDto = pharmacistDto;
        this.patientId= patientId;
        this.pharmacistName = pharmacistName;
        this.pharmacistLastName = pharmacistLastName;
        this.pharmacistId = pharmacistId;
        this.pharmacistEvaluationGrade = pharmacistEvaluationGrade;
        this.durationMinutes = durationMinutes;
    }


}
