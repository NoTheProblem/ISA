package ftn.isa.pharmacy.model;

import javax.persistence.*;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @Column
    private Long idOfEvaluated;

    @Column
    private String typeOfEvaluation;


}
