package ftn.isa.pharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.mapper.impl.PharmacyMapperImpl;
import ftn.isa.pharmacy.service.PharmacyService;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

    private final PharmacyService pharmacyService;
    private final PharmacyMapperImpl pharmacyMapper;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService, PharmacyMapperImpl pharmacyMapper) {
        this.pharmacyService = pharmacyService;
        this.pharmacyMapper = pharmacyMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PharmacyDto>> getAll() {
        Collection<PharmacyDto> pharmacyDtoList = pharmacyMapper.entity2Bean(pharmacyService.getAll());
        return ResponseEntity.ok(pharmacyDtoList);
    }

}
