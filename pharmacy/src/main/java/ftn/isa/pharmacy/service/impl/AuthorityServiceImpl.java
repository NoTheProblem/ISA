package ftn.isa.pharmacy.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.pharmacy.model.Authority;
import ftn.isa.pharmacy.repository.AuthorityRepository;
import ftn.isa.pharmacy.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    // TODO koristi konstruktor za sve dependency
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findById(Long id) {
        Authority auth = this.authorityRepository.getOne(id);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    @Override
    public List<Authority> findByName(String name) {
        Authority auth = this.authorityRepository.findByName(name);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }


}
