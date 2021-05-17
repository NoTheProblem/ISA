package ftn.isa.pharmacy.dto;
import java.util.Date;

public class PurchaseOrderDTO {
    Long id;
    Date endDate;
    String status;
    Long adminID;
    Long medicineQuantityID;
    Long supplierID;
    float price;

    public PurchaseOrderDTO() {
    }

    public PurchaseOrderDTO(Long id, Date endDate, String status, Long adminID, Long medicineQuantityID, Long supplierID, float price) {
        this.id = id;
        this.endDate = endDate;
        this.status = status;
        this.adminID = adminID;
        this.medicineQuantityID = medicineQuantityID;
        this.supplierID = supplierID;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public Long getMedicineQuantityID() {
        return medicineQuantityID;
    }

    public void setMedicineQuantityID(Long medicineQuantityID) {
        this.medicineQuantityID = medicineQuantityID;
    }

    public Long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
