package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.repository.PharmacyRepository;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    // https://www.vojtechruzicka.com/field-dependency-injection-considered-harmful/#gatsby-focus-wrapper
    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }
}
