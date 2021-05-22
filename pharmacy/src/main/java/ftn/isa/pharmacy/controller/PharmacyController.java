package ftn.isa.pharmacy.controller;

import java.util.Collection;
import java.util.List;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.mapper.impl.DermatologistMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PharmacistMapperImpl;
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
    private final DermatologistMapperImpl dermatologistMapper;
    private final PharmacistMapperImpl pharmacistMapper;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService, PharmacyMapperImpl pharmacyMapper, DermatologistMapperImpl dermatologistMapper, PharmacistMapperImpl pharmacistMapper) {
        this.pharmacyService = pharmacyService;
        this.pharmacyMapper = pharmacyMapper;
        this.dermatologistMapper = dermatologistMapper;
        this.pharmacistMapper = pharmacistMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PharmacyDto>> getAll() {
        Collection<PharmacyDto> pharmacyDtoList = pharmacyMapper.entity2Bean(pharmacyService.getAll());
        return ResponseEntity.ok(pharmacyDtoList);
    }

    @GetMapping(value = "/unauth/{id}")
    public ResponseEntity<PharmacyDto> getPharmacyByID(@PathVariable Long id) {
        PharmacyDto pharmacyDto = pharmacyMapper.entity2Bean(pharmacyService.getById(id));
        return ResponseEntity.ok(pharmacyDto);
    }

    @GetMapping(value = "/admin")
    public ResponseEntity<PharmacyDto> getPharmacyByID() {
        PharmacyDto pharmacyDto = pharmacyMapper.entity2Bean(pharmacyService.getPharmacyByAdmin());
        return ResponseEntity.ok(pharmacyDto);
    }

    @GetMapping(value = "/subscribe/{id}")
    public Boolean subscribe(@PathVariable Long id) {
        return pharmacyService.subscribe(id);
    }

    @GetMapping(value = "/getDerma/{id}")
    public ResponseEntity<Collection<DermatologistDto>> getDerma(@PathVariable Long id) {
        Collection<DermatologistDto> dermatologistDtoList = dermatologistMapper.entity2Bean(pharmacyService.getDermaByPhaID(id));
        return ResponseEntity.ok(dermatologistDtoList);
    }

    @GetMapping(value = "/getPharma/{id}")
    public ResponseEntity<Collection<PharmacistDTO>> getPharma(@PathVariable Long id) {
        Collection<PharmacistDTO> pharmacistDTOS = pharmacistMapper.entity2Bean(pharmacyService.getPharmaByPhaID(id));
        return ResponseEntity.ok(pharmacistDTOS);
    }

    @GetMapping(value = "/getDerma/phaadmin")
    public ResponseEntity<Collection<DermatologistDto>> getDermaForPhaAdmin() {
        Collection<DermatologistDto> dermatologistDtoList = pharmacyService.getDermaForPhaAdmin();
        return ResponseEntity.ok(dermatologistDtoList);
    }

}
