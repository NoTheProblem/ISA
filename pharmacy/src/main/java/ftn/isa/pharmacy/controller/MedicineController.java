package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.mapper.impl.MedicineMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PharmacyMapperImpl;
import ftn.isa.pharmacy.service.MedicineService;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;
    private final MedicineMapperImpl medicineMapper;

    @Autowired
    public MedicineController(MedicineService medicineService, MedicineMapperImpl medicineMapper) {
        this.medicineService = medicineService;
        this.medicineMapper = medicineMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<MedicineDto>> getAll() {
        Collection<MedicineDto> medicineDtoList = medicineMapper.entity2Bean(medicineService.getAll());
        return ResponseEntity.ok(medicineDtoList);
    }

}

