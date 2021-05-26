package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.repository.DermatologistRepository;
import ftn.isa.pharmacy.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistServiceImpl implements DermatologistService  {

    private final DermatologistRepository dermatologistRepository;

    @Autowired
    public DermatologistServiceImpl(DermatologistRepository dermatologistRepository) {
        this.dermatologistRepository = dermatologistRepository;
    }

    @Override
    public List<Dermatologist> getAll() {
        return dermatologistRepository.findAll();
    }


}
