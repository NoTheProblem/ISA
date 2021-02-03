package ftn.isa.pharmacy.dto;

import ftn.isa.pharmacy.model.Pharmacy;

public class PharmacyDto {

    private Long id;

    private String name;

    private String country;

    private String city;

    private String address;

    private String pharmacyDescription;

    private float evaluationGrade;

    private float counselingPrice;

    public PharmacyDto() {
    }

    public PharmacyDto(Pharmacy pharmacy) {
        this.name = pharmacy.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPharmacyDescription() {
        return pharmacyDescription;
    }

    public void setPharmacyDescription(String pharmacyDescription) {
        this.pharmacyDescription = pharmacyDescription;
    }

    public float getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(float evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public float getCounselingPrice() {
        return counselingPrice;
    }

    public void setCounselingPrice(float counselingPrice) {
        this.counselingPrice = counselingPrice;
    }
}
