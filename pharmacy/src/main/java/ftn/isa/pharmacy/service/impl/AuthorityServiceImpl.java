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

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority findById(Long id) {
        Authority auth = this.authorityRepository.getOne(id);
        //List<Authority> auths = new ArrayList<>();
        //auths.add(auth);
        return auth;
    }

    @Override
    public Authority findByname(String name) {
        Authority auth = this.authorityRepository.findByName(name);
        //List<Authority> auths = new ArrayList<>();
        //auths.add(auth);
        return auth;
    }


}