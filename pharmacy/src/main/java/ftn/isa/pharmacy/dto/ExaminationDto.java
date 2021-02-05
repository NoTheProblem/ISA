package ftn.isa.pharmacy.dto;

import ftn.isa.pharmacy.model.Examination;

import javax.persistence.Column;
import java.sql.Time;
import java.util.Date;

public class ExaminationDto {


    private Long id;

    private Boolean penalty;

    private Boolean isFree;

    private String examinationReport;

    private int loyaltyScore;

    private Date date;

    private Time time;

    private int durationMinutes;

    private float price;

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

    public String getExaminationReport() {
        return examinationReport;
    }

    public void setExaminationReport(String examinationReport) {
        this.examinationReport = examinationReport;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ExaminationDto(Long id, Boolean penalty, Boolean isFree, String examinationReport, int loyaltyScore, Date date, Time time, int durationMinutes, float price) {
        this.id = id;
        this.penalty = penalty;
        this.isFree = isFree;
        this.examinationReport = examinationReport;
        this.loyaltyScore = loyaltyScore;
        this.date = date;
        this.time = time;
        this.durationMinutes = durationMinutes;
        this.price = price;
    }

    public ExaminationDto() {
    }

    public ExaminationDto(Examination examination) {
        this.id = examination.getId();
        this.penalty = examination.getPenalty();
        this.isFree = examination.getFree();
        this.examinationReport = examination.getExaminationReport();
        this.loyaltyScore = examination.getLoyaltyScore();;
        this.date = examination.getDate();
        this.time = examination.getTime();
        this.durationMinutes = examination.getDurationMinutes();
        this.price = examination.getPrice();
    }




}
