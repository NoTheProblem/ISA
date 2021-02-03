package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.mapper.PharmacyMapper;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

    private final PharmacyService pharmacyService;
    private final PharmacyMapper pharmacyMapper;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService, PharmacyMapper pharmacyMapper) {
        this.pharmacyService = pharmacyService;
        this.pharmacyMapper = pharmacyMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<PharmacyDto>> getAll() {
        List<PharmacyDto> pharmacyDtoList = pharmacyMapper.entitiesToBeans(pharmacyService.getAll());
        return ResponseEntity.ok(pharmacyDtoList);
    }

}
