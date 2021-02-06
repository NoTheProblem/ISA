package ftn.isa.pharmacy.dto;

import javax.persistence.Column;
import java.util.Date;

public class ReservationDto {

    private Long id;

    private Boolean pickedUp;

    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(Boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    private float price;

    private Long pharmacyId;

    private Long medicationId;



}
