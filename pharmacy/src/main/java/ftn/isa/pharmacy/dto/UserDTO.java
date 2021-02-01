package ftn.isa.pharmacy.dto;

import ftn.isa.pharmacy.model.User;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

public class UserDTO {

    private Long id;
    private String  firstName;
    private String  lastName;
    private String  username;
    private String  password;
    private String  email;
    private String country;
    private String city;
    private String address;
    private String phoneNumber;
    private Date birthDate;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.address = user.getAddress();
        this.phoneNumber = user.getPhoneNumber();
        this.birthDate = user.getBirthDate();
    }


    public UserDTO(Long id, String firstName, String lastName, String username, String password, String email, String country, String city, String address, String phoneNumber, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
