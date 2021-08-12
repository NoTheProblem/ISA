package ftn.isa.pharmacy.dto;

import java.sql.Time;
import java.util.Date;

public class ReservationDTO {

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "medicineid=" + medicineid +
                ", pharmacyid=" + pharmacyid +
                ", pickedUp=" + pickedUp +
                ", pickedUpDate=" + pickedUpDate +
                ", pickedUpTime='" + pickedUpTime + '\'' +
                ", endDate=" + endDate +
                ", endTime='" + endTime + '\'' +
                ", id=" + id +
                '}';
    }

    private int medicineid;

    public int getMedicineid() {
        return medicineid;
    }

    public void setMedicineid(int medicineid, int pharmacyid, boolean pickedUp, Date pickedUpDate, String pickedUpTime, Date endDate, String endTime, Long id, String medicineName, String pharmacyName) {
        this.medicineid = medicineid;
        this.pharmacyid = pharmacyid;
        this.pickedUp = pickedUp;
        this.pickedUpDate = pickedUpDate;
        this.pickedUpTime = pickedUpTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.id = id;
        this.medicineName = medicineName;
        this.pharmacyName = pharmacyName;
    }

    public ReservationDTO() {
    }

    public ReservationDTO(int medicineid) {
        this.medicineid = medicineid;
    }

    public int getPharmacyid() {
        return pharmacyid;
    }

    public void setPharmacyid(int pharmacyid) {
        this.pharmacyid = pharmacyid;
    }

    private int pharmacyid;

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    private boolean pickedUp;

    public Date getPickedUpDate() {
        return pickedUpDate;
    }

    public void setPickedUpDate(Date pickedUpDate) {
        this.pickedUpDate = pickedUpDate;
    }

    private Date pickedUpDate;

    public String getPickedUpTime() {
        return pickedUpTime;
    }

    public void setPickedUpTime(String pickedUpTime) {
        this.pickedUpTime = pickedUpTime;
    }

    private String pickedUpTime;

    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private String endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    private String medicineName;

    private String pharmacyName;

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
