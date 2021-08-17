package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.PromotionDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.PromotionMapper;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.PatientRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.PharmacyRepository;
import ftn.isa.pharmacy.repository.PromotionRepository;
import ftn.isa.pharmacy.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final PromotionMapper promotionMapper;
    private final MailServiceImpl mailService;
    private final PharmacyRepository pharmacyRepository;
    private final PatientRepository patientRepository;


    @Autowired
    public PromotionServiceImpl(PatientRepository patientRepository,PromotionRepository promotionRepository, PharmacyAdminRepository pharmacyAdminRepository, PromotionMapper promotionMapper, MailServiceImpl mailService, PharmacyRepository pharmacyRepository) {
        this.promotionRepository = promotionRepository;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.promotionMapper = promotionMapper;
        this.mailService = mailService;
        this.pharmacyRepository = pharmacyRepository;
        this.patientRepository  = patientRepository;
    }

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }

    @Override
    public List<Promotion> getAllActive() {
        Date date = new Date();
        List<Promotion> promotions = promotionRepository.findAllByEndDateAfter(date);
        if(promotions.isEmpty()){
            throw new ResourceConflictException(1L,"Ne postoje promocije/akcije");
        }
        return  promotions;
     }

    @Override
    public void addPromotion(PromotionDTO promotionDTO) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        if(promotionDTO.getEndDate().before(promotionDTO.getStartDate())){
            throw new ResourceConflictException(1L,"Greska u datumima");
        }
        Promotion promotion = promotionMapper.bean2Entity(promotionDTO);
        promotion.setPharmacy(pharmacyAdmin.getPharmacy());
        promotionRepository.save(promotion);
        Pharmacy pharmacy = promotion.getPharmacy();
        for (Patient patinet:pharmacy.getSubscribedPatients())
        {
            mailService.newPromotionNotification(promotion,patinet);
        }
    }

    @Override
    public Collection<Promotion> getAllByPharmacyID(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        return  promotionRepository.findAllByPharmacy(pharmacy);
    }

    @Override
    public Collection<Promotion> getAllActiveByPharmacyID(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        Date date = new Date();
        return  promotionRepository.findAllByPharmacyAndEndDateAfter(pharmacy,date);
    }

    @Override
    public Collection<Promotion> getAllActiveForPharmacyAdmin() {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Date date = new Date();
        return  promotionRepository.findAllByPharmacyAndEndDateAfter(pharmacy,date);

    }

    @Override
    public Collection<Promotion> getAllForPharmacyAdmin() {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        return  promotionRepository.findAllByPharmacy(pharmacy);
    }


    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            return pharmacyAdminOptional.get();
        }
        throw new ResourceConflictException(1L,"Ne postoji administrator apoteke!");
    }

    @Override
    public Collection<Pharmacy> getAllPharmacyForPatient(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        List<Promotion> promotions = promotionRepository.findAllByPatient(patientOptional.get());
        List<Pharmacy> pharmacies = new Stack<>();
        for (Promotion promotion: promotions) {
            pharmacies.add(promotion.getPharmacy());
        }
        return pharmacies;
    }



}
