package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.PromotionDTO;
import ftn.isa.pharmacy.mapper.PromotionMapper;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.PromotionRepository;
import ftn.isa.pharmacy.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final PromotionMapper promotionMapper;
    private final MailServiceImpl mailService;


    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository, PharmacyAdminRepository pharmacyAdminRepository, PromotionMapper promotionMapper, MailServiceImpl mailService) {
        this.promotionRepository = promotionRepository;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.promotionMapper = promotionMapper;
        this.mailService = mailService;
    }

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }

    @Override
    public List<Promotion> getAllActive() {
        List<Promotion> promotions = promotionRepository.findAll();
        Date date = new Date();
        if(promotions.isEmpty()){
            return null;
            //TODO exception
        }
        for(Promotion p : promotions){
            if (p.getEndDate().before(date))
            promotions.remove(p);
        }

        return  promotions;
     }

    @Override
    public void addPromotion(PromotionDTO promotionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            Promotion promotion = promotionMapper.bean2Entity(promotionDTO);
            promotion.setPharmacy(pharmacyAdmin.getPharmacy());
            promotionRepository.save(promotion);
            Pharmacy pharmacy = promotion.getPharmacy();
            for (Patient patinet:pharmacy.getSubscribedPatients())
            {
                mailService.newPromotionNotification(promotion,patinet);
            }
        }
        //TODO exception
    }

}
