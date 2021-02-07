package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.PharmacyRepository;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;

    // https://www.vojtechruzicka.com/field-dependency-injection-considered-harmful/#gatsby-focus-wrapper
    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository, PharmacyAdminRepository pharmacyAdminRepository) {
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Pharmacy getById(Long id) {
        Optional<Pharmacy> pharmacyOptional =  pharmacyRepository.findById(id);
        if(pharmacyOptional.isPresent()){
            Pharmacy pharmacy = pharmacyOptional.get();
            return pharmacy;
        }
        //TODO : throw exception
        return  null;
    }

    @Override
    public Pharmacy getPharmacyByAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
            return pharmacy;
        }
        //TODO : throw exception
        return null;
    }
}
