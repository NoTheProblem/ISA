package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date endDate;

    @Column
    private String status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Bid> bids = new HashSet<Bid>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PharmacyAdmin pharmacyAdmin;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityOrder> orderMedicines = new HashSet<MedicineQuantityOrder>();

    public PurchaseOrder() {
    }

    public PurchaseOrder(Long id, Date endDate, String status, Set<Bid> bids, PharmacyAdmin pharmacyAdmin, Set<MedicineQuantityOrder> orderMedicines) {
        this.id = id;
        this.endDate = endDate;
        this.status = status;
        this.bids = bids;
        this.pharmacyAdmin = pharmacyAdmin;
        this.orderMedicines = orderMedicines;
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

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public PharmacyAdmin getPharmacyAdmin() {
        return pharmacyAdmin;
    }

    public void setPharmacyAdmin(PharmacyAdmin pharmacyAdmin) {
        this.pharmacyAdmin = pharmacyAdmin;
    }

    public Set<MedicineQuantityOrder> getOrderMedicines() {
        return orderMedicines;
    }

    public void setOrderMedicines(Set<MedicineQuantityOrder> orderMedicines) {
        this.orderMedicines = orderMedicines;
    }
}
