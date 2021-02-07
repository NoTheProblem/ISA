package ftn.isa.pharmacy.service;


import ftn.isa.pharmacy.dto.PromotionDTO;
import ftn.isa.pharmacy.model.Promotion;

import java.util.List;

public interface PromotionService {
    List<Promotion> getAll();
    List<Promotion> getAllActive();
    void addPromotion(PromotionDTO promotionDTO);

}
