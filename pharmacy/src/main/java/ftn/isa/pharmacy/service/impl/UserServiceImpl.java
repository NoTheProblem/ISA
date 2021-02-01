package ftn.isa.pharmacy.service.impl;


import java.util.List;

import ftn.isa.pharmacy.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.isa.pharmacy.model.Authority;
import ftn.isa.pharmacy.model.User;
import ftn.isa.pharmacy.repository.UserRepository;
import ftn.isa.pharmacy.service.AuthorityService;
import ftn.isa.pharmacy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        return u;
    }

    public User findOne(Long id) throws AccessDeniedException {
        User u = userRepository.findById(id).orElseGet(null);
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User save(UserDTO userDTO) {
        User u = new User();
        u.setUsername(userDTO.getUsername());
        // pre nego sto postavimo lozinku u atribut hesiramo je
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setAddress(userDTO.getAddress());
        u.setBirthDate(userDTO.getBirthDate());
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setEmail(userDTO.getEmail());
        u.setPassword(userDTO.getPassword());
        u.setUsername(userDTO.getUsername());
        u.setPhoneNumber(userDTO.getPhoneNumber());
        u.setEnabled(true);

        Authority auth = authService.findByname("ROLE_USER");
        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        u.setAuthority(auth);

        u = this.userRepository.save(u);
        return u;
    }

}

