package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.MedicineRegisterDto;
import ftn.isa.pharmacy.mapper.impl.MedicineMapperImpl;
import ftn.isa.pharmacy.mapper.impl.MedicineRegisterMapperImpl;
import ftn.isa.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;
    private final MedicineMapperImpl medicineMapper;
    private final MedicineRegisterMapperImpl medicineRegisterMapper;

    @Autowired
    public MedicineController(MedicineService medicineService, MedicineMapperImpl medicineMapper, MedicineRegisterMapperImpl medicineRegisterMapper) {
        this.medicineService = medicineService;
        this.medicineMapper = medicineMapper;
        this.medicineRegisterMapper = medicineRegisterMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<MedicineDto>> getAll() {
        Collection<MedicineDto> medicineDtoList = medicineMapper.entity2Bean(medicineService.getAll());
        return ResponseEntity.ok(medicineDtoList);
    }


    @GetMapping(value = "/getAllReg")
    public ResponseEntity<Collection<MedicineRegisterDto>> getAllReg() {
        Collection<MedicineRegisterDto> medicineRegisterDto = medicineRegisterMapper.entity2Bean(medicineService.getAll());
        return ResponseEntity.ok(medicineRegisterDto);
    }

    @PostMapping("/addMedicine")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    public void addMedicine(@RequestBody MedicineRegisterDto medicineRegisterDto) {
        medicineService.addMedicine(medicineRegisterDto);
    }

}

