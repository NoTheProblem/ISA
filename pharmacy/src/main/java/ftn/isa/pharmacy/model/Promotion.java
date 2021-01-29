package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String type;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @ManyToMany(mappedBy = "subscribedPromotions")
    private Set<Patient> subscribedPatients = new HashSet<Patient>();


    public Promotion() {
    }

    public Promotion(Long id, String title, String text, Date startDate, Date endDate, String type) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }
    public Promotion(Promotion promotion) {
        this.id = promotion.id;
        this.title = promotion.title;
        this.text = promotion.text;
        this.startDate = promotion.startDate;
        this.endDate = promotion.endDate;
        this.type = promotion.type;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setType(String type) {
        this.type = type;
    }
}

