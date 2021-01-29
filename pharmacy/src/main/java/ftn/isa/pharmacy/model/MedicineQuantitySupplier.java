package ftn.isa.pharmacy.model;


import javax.persistence.*;

@Entity
public class MedicineQuantitySupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Medicine medicine;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Supplier supplier;
}
