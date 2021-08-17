package ftn.isa.pharmacy.dto;


import ftn.isa.pharmacy.model.Pharmacy;

import java.util.Date;

public class PromotionDTO {
    private long id;
    private String title;
    private String text;
    private Date startDate;
    private Date endDate;
    private String type;

    public PatientDTO getPatientDTO() {
        return patientDTO;
    }

    public void setPatientDTO(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
    }

    public PharmacyDto getPharmacyDTO() {
        return pharmacyDTO;
    }

    public void setPharmacyDTO(PharmacyDto pharmacyDTO) {
        this.pharmacyDTO = pharmacyDTO;
    }

    private PatientDTO patientDTO;
    private PharmacyDto pharmacyDTO;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
