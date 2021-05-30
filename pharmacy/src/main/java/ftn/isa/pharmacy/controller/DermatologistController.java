package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.mapper.impl.DermatologistMapperImpl;
import ftn.isa.pharmacy.service.DermatologistService;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/dermatologist")
public class DermatologistController {

    private final DermatologistService dermatologistService;
    private final DermatologistMapperImpl dermatologistMapper;

    @Autowired
    public DermatologistController(DermatologistService dermatologistService, DermatologistMapperImpl dermatologistMapper) {
        this.dermatologistService = dermatologistService;
        this.dermatologistMapper = dermatologistMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<DermatologistDto>> getAll() {
        Collection<DermatologistDto> dermatologistDtoList = dermatologistService.getAll();
        return ResponseEntity.ok(dermatologistDtoList);
    }

}
