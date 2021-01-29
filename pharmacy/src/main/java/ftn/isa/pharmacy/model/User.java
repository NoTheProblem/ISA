package ftn.isa.pharmacy.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String phoneNumber;

    @Column
    private Date birthDate;

}
