package ftn.isa.pharmacy.model;


import javax.persistence.*;

@Entity(name = "pricelist")
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
