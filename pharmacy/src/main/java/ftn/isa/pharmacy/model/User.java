package ftn.isa.pharmacy.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static javax.persistence.DiscriminatorType.STRING;

@Entity(name="registeredusers")
@Table
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(name="tip", discriminatorType=STRING)
public abstract class  User {

    @Id
    @SequenceGenerator(name = "mySeqGenV2", sequenceName = "mySeqV2", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(nullable = false)
    private String  firstName;

    @Column(nullable = false)
    private String  lastName;

    @Column(nullable = false,unique = true)
    private String  username;

    @Column(nullable = false)
    private String  password;

    @Column(nullable = false, unique = true)
    private String  email;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private Date birthDate;


    public User() {
    }

    public User(Long id, String firstName, String lastName, String username, String password, String email, String country, String city, String adress, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.city = city;
        this.address = adress;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAdress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String drzava) {
        this.country = drzava;
    }

    public void setCity(String grad) {
        this.city = grad;
    }

    public void setAdress(String adresa) {
        this.address = adresa;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
