package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.PriceMediceDTO;
import ftn.isa.pharmacy.dto.UserDTO;
import ftn.isa.pharmacy.mapper.impl.MedicineMapperImpl;
import ftn.isa.pharmacy.model.User;
import ftn.isa.pharmacy.service.MedicineService;
import ftn.isa.pharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ftn.isa.pharmacy.dto.MedicineRegisterDto;
import ftn.isa.pharmacy.mapper.impl.MedicineRegisterMapperImpl;
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
    private UserService userService;

    @Autowired
    public MedicineController(MedicineService medicineService, MedicineMapperImpl medicineMapper,MedicineRegisterMapperImpl medicineRegisterMapper) {
        this.medicineService = medicineService;
        this.medicineMapper = medicineMapper;
        this.medicineRegisterMapper = medicineRegisterMapper;

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

    @GetMapping(value = "/getMissingMedicines")
    public ResponseEntity<Collection<MedicineDto>> getMissingMedicines() {
        Collection<MedicineDto> medicineDtoList = medicineMapper.entity2Bean(medicineService.getMissingMedicines());
        return ResponseEntity.ok(medicineDtoList);
    }

    @PostMapping("/removeMedicineFromPhamracy")
    public void removeMedicineFromPhamracy(@RequestBody MedicineDto medicineDto) {
        medicineService.removeMedicineFromPhamracy(medicineDto);
    }

    @GetMapping(value = "/getAvailableMedicines")
    public ResponseEntity<Collection<MedicineDto>> getAvailableMedicines() {
        Collection<MedicineDto> medicineDtoList = medicineMapper.entity2Bean(medicineService.getAvailableMedicines());
        return ResponseEntity.ok(medicineDtoList);
    }

    @GetMapping(value = "/getAllHistoryReservationForEvaluation")
    public ResponseEntity<Collection<MedicineDto>> getAllHistoryReservationForEvaluation() {
        Collection<MedicineDto> medicineDtoList = medicineMapper.entity2Bean(medicineService.getAllHistoryReservationForEvaluation());
        return ResponseEntity.ok(medicineDtoList);
    }



}

