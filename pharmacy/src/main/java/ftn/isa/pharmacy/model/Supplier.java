package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantitySupplier> medicines = new HashSet<MedicineQuantitySupplier>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Bid> bids = new HashSet<Bid>();

    public Supplier() {
    }

    public Supplier(Long id, Set<MedicineQuantitySupplier> medicines, Set<Bid> bids) {
        this.id = id;
        this.medicines = medicines;
        this.bids = bids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MedicineQuantitySupplier> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<MedicineQuantitySupplier> medicines) {
        this.medicines = medicines;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
}
