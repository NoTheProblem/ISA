package ftn.isa.pharmacy.service;

import java.util.List;

import ftn.isa.pharmacy.model.SysAdmin;
import ftn.isa.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogInService {

    @Autowired
    private UserRepository userRepository;


    public SysAdmin findOne(Long id) {
        return userRepository.findById(id).orElseGet(null);
    }

    public List<SysAdmin> findByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    public List<SysAdmin> findAll() {
        return userRepository.findAll();

    }



}
