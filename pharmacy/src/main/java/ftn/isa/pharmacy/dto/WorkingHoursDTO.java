package ftn.isa.pharmacy.dto;

import java.sql.Time;

public class WorkingHoursDTO {

    private Long id;
    private Time startTime;
    private Time endTime;
    private PharmacyDto pharmacy;
    private DermatologistDto dermatologist;

    public WorkingHoursDTO() {
    }

    public WorkingHoursDTO(Long id, Time startTime, Time endTime, PharmacyDto pharmacy, DermatologistDto dermatologist) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pharmacy = pharmacy;
        this.dermatologist = dermatologist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public PharmacyDto getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDto pharmacy) {
        this.pharmacy = pharmacy;
    }

    public DermatologistDto getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(DermatologistDto dermatologist) {
        this.dermatologist = dermatologist;
    }


}
