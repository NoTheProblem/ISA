package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class PatientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/addAllergy")
    public void addAllergy(@RequestBody MedicineDto medicineDto) {
        patientService.addAllergy(medicineDto);
    }

}
