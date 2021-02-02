package ftn.isa.pharmacy.service;

import java.util.List;

import ftn.isa.pharmacy.dto.UserDTO;
import ftn.isa.pharmacy.model.User;

public interface UserService {

    User findOne(Long id);
    User findByUsername(String username);
    List<User> findAll ();
    User save(UserDTO userDTO);


}

