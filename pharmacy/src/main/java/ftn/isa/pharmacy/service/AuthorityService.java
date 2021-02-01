package ftn.isa.pharmacy.service;


import java.util.List;

import ftn.isa.pharmacy.model.Authority;

public interface AuthorityService {
    Authority findById(Long id);
    Authority findByname(String name);
}