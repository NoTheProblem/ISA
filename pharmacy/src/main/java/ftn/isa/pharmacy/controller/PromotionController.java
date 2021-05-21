package ftn.isa.pharmacy.controller;


import ftn.isa.pharmacy.dto.PromotionDTO;
import ftn.isa.pharmacy.mapper.impl.PromotionMapperImpl;
import ftn.isa.pharmacy.repository.WorkingHoursRepository;
import ftn.isa.pharmacy.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Collection;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    private final PromotionService promotionService;
    private final PromotionMapperImpl promotionMapper;

    @Autowired
    public PromotionController(PromotionService promotionService, PromotionMapperImpl promotionMapper, WorkingHoursRepository workingHoursRepository) {
        this.promotionService = promotionService;
        this.promotionMapper = promotionMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PromotionDTO>> getAll() {
        Collection<PromotionDTO> promotionDTOS = promotionMapper.entity2Bean(promotionService.getAll());
        return ResponseEntity.ok(promotionDTOS);
    }

    @GetMapping(value = "/getAllActive")
    public ResponseEntity<Collection<PromotionDTO>> getAllActive() {
        Collection<PromotionDTO> promotionDTOS = promotionMapper.entity2Bean(promotionService.getAllActive());
        return ResponseEntity.ok(promotionDTOS);
    }

    @PostMapping("/addPromotion")
    public void addAllergy(@RequestBody PromotionDTO promotionDTO) {
        promotionService.addPromotion(promotionDTO);
    }



}
