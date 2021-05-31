package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.mapper.impl.PharmacistMapperImpl;
import ftn.isa.pharmacy.service.PharmacistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/pharmacist")
public class PharmacistController {

    private final PharmacistMapperImpl pharmacistMapper;
    private final PharmacistService pharmacistService;

    public PharmacistController(PharmacistMapperImpl pharmacistMapper, PharmacistService pharmacistService) {
        this.pharmacistMapper = pharmacistMapper;
        this.pharmacistService = pharmacistService;
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PharmacistDTO>> getAll() {
        Collection<PharmacistDTO>  pharmacistDTOS= pharmacistService.getAll();
        return ResponseEntity.ok(pharmacistDTOS);
    }
}
