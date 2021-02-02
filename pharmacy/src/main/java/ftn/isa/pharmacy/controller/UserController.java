package ftn.isa.pharmacy.controller;


import ftn.isa.pharmacy.dto.UserDTO;
import ftn.isa.pharmacy.model.User;
import ftn.isa.pharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<User> users = userService.findAll();

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User u : users) {
            usersDTO.add(new UserDTO(u));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {

        User user = userService.findOne(id);


        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }
/* IMA URAJDENO U USERSERVISU I USER SERVICE IMPL

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setBirthDate(userDTO.getBirthDate());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {

        // a user must exist
        User user = userService.findOne(userDTO.getId());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setBirthDate(userDTO.getBirthDate());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());


        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        User user = userService.findOne(id);

        if (user != null) {
            userService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
    }












