package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_SUPPLIER")
public class Supplier extends User {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantitySupplier> medicines = new HashSet<MedicineQuantitySupplier>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Bid> bids = new HashSet<Bid>();

    public Supplier() {
    }

    public Supplier(Long id, Set<MedicineQuantitySupplier> medicines, Set<Bid> bids) {
        this.medicines = medicines;
        this.bids = bids;
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
