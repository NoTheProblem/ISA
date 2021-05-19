package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.PatientRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.PharmacyRepository;
import ftn.isa.pharmacy.repository.PromotionRepository;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.List;
import java.util.Optional;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final PatientRepository patientRepository;
    private final PromotionRepository promotionRepository;
    private final MailServiceImpl mailService;

    // https://www.vojtechruzicka.com/field-dependency-injection-considered-harmful/#gatsby-focus-wrapper
    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository, PharmacyAdminRepository pharmacyAdminRepository, PatientRepository patientRepository, PromotionRepository promotionRepository, MailServiceImpl mailService) {
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.patientRepository = patientRepository;
        this.promotionRepository = promotionRepository;
        this.mailService = mailService;
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

    @Override
    public Boolean subscribe(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            Set<Pharmacy> promotionSet = patient.getSubscribedPharmacies();
            Pharmacy pharmacy = pharmacyRepository.getOne(id);
            promotionSet.add(pharmacy);
            patient.setSubscribedPharmacies(promotionSet);
            patientRepository.saveAndFlush(patient);
            mailService.newSubscriptionForPromotion(pharmacy,patient);
            return true;
        }
        return false;
    }

    @Override
    public Set<Dermatologist> getDermaByPhaID(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        Set<Dermatologist> dermatologists = pharmacy.getDermatologists();
        return dermatologists;
    }

    @Override
    public Set<Pharmacist> getPharmaByPhaID(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        Set<Pharmacist> pharmacists =  pharmacy.getPharmacists();
        return  pharmacists;
    }
}
