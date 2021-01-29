package ftn.isa.pharmacy.model;

import javax.persistence.*;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String complaintText;

    @Column
    private String status;

    @Column
    private String complaintAnswer;

    @Column
    private Long idOfSubject;

    @Column
    private String typeOfComplaint;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @Column
    private Long idOfAdmin;

}
