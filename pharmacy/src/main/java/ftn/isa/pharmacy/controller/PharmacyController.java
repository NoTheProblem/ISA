package ftn.isa.pharmacy.controller;

import java.util.Collection;

import ftn.isa.pharmacy.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<PharmacyDto> getPharmacyByID(@PathVariable Long id) {
        PharmacyDto pharmacyDto = pharmacyMapper.entity2Bean(pharmacyService.getById(id));
        return ResponseEntity.ok(pharmacyDto);
    }

    @GetMapping(value = "/admin")
    public ResponseEntity<PharmacyDto> getPharmacyByID() {
        PharmacyDto pharmacyDto = pharmacyMapper.entity2Bean(pharmacyService.getPharmacyByAdmin());
        return ResponseEntity.ok(pharmacyDto);
    }




}
