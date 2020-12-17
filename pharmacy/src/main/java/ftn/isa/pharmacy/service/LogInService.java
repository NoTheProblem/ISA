package ftn.isa.pharmacy.service;

import java.util.List;

import ftn.isa.pharmacy.model.Sysadmin;
import ftn.isa.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogInService {

    @Autowired
    private UserRepository userRepository;


    public Sysadmin findOne(Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    public List<Sysadmin> findByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    public List<Sysadmin> findAll() {
        return userRepository.findAll();

    }



}
