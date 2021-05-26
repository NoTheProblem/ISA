package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.AbsenceDTO;
import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.PriceMediceDTO;
import ftn.isa.pharmacy.mapper.PriceMediceMapper;
import ftn.isa.pharmacy.mapper.impl.MedicineMapperImpl;
import ftn.isa.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;
    private final MedicineMapperImpl medicineMapper;
    private final PriceMediceMapper priceMediceMapper;

    @Autowired
    public MedicineController(MedicineService medicineService, MedicineMapperImpl medicineMapper, PriceMediceMapper priceMediceMapper) {
        this.medicineService = medicineService;
        this.medicineMapper = medicineMapper;
        this.priceMediceMapper = priceMediceMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<MedicineDto>> getAll() {
        Collection<MedicineDto> medicineDtoList = medicineMapper.entity2Bean(medicineService.getAll());
        return ResponseEntity.ok(medicineDtoList);
    }

    @GetMapping(value = "/getMedicinesForPhaAdmin")
    public ResponseEntity<Collection<MedicineDto>> getMedicinesForPhaAdmin() {
        Collection<MedicineDto> medicineDtoList = medicineMapper.entity2Bean(medicineService.getMedicinesForPhaAdmin());
        return ResponseEntity.ok(medicineDtoList);
    }

    @GetMapping(value = "/getMedPriceForPhaAdmin/{id}")
    public ResponseEntity<PriceMediceDTO> getMedPriceForPhaAdmin(@PathVariable Long id) {
        PriceMediceDTO priceMediceDTO = medicineService.getMedPriceForPhaAdmin(id);
        return ResponseEntity.ok(priceMediceDTO);
    }

    @PostMapping("/addNewMedPrice")
    public void addNewMedPrice(@RequestBody PriceMediceDTO priceMediceDTO) {
        medicineService.addNewMedPrice(priceMediceDTO);
    }

}

