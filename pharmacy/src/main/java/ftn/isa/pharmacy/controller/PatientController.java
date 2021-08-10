package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.*;
import ftn.isa.pharmacy.mapper.LoyaltyProgramMapper;
import ftn.isa.pharmacy.mapper.MedicineMapper;
import ftn.isa.pharmacy.mapper.impl.ExaminationMapperImpl;
import ftn.isa.pharmacy.mapper.impl.LoyaltyProgramMapperImpl;
import ftn.isa.pharmacy.mapper.impl.MedicineMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PatientMapperImpl;
import ftn.isa.pharmacy.model.Examination;
import ftn.isa.pharmacy.model.LoyaltyProgram;
import ftn.isa.pharmacy.model.User;
import ftn.isa.pharmacy.service.ExaminationService;
import ftn.isa.pharmacy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/patient")
// Nije samo za korisnika
public class PatientController {

    // private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;
    private final PatientMapperImpl patientMapper;
    private final ExaminationMapperImpl examinationMapper;
    private final ExaminationService examinationService;
    private final MedicineMapperImpl medicineMapper;
    private final LoyaltyProgramMapperImpl loyaltyProgramMapper;
    @Autowired
    public PatientController(PatientService patientService, PatientMapperImpl patientMapper, MedicineMapperImpl medicineMapper, LoyaltyProgramMapperImpl loyaltyProgramMapper,
                             ExaminationMapperImpl examinationMapper, ExaminationService examinationService     ) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.medicineMapper = medicineMapper;
        this.loyaltyProgramMapper = loyaltyProgramMapper;
        this.examinationMapper = examinationMapper;
        this.examinationService  = examinationService;
    }

    @PostMapping("/addAllergy")
    public void addAllergy(@RequestBody MedicineDto medicineDto) {
        patientService.addAllergy(medicineDto);
    }

    @PostMapping("/addExamination")
    public void addExamination(@RequestBody ExaminationDto examinationDto) {
        patientService.addExamination(examinationDto);
    }

    @PostMapping("/cancelExamination")
    public void cancelExamination(@RequestBody ExaminationDto examinationDto) {
        patientService.addExamination(examinationDto);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PatientDTO>> getAll() {
        Collection<PatientDTO> userDtoList = patientMapper.entity2Bean(patientService.getAll());
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping(value = "/getAllAllergyForPatient/{username}")
    public ResponseEntity<Collection<MedicineDto>> getAllAllergyForPatient(@PathVariable String username) {
        Collection<MedicineDto> allergyDtoList = medicineMapper.entity2Bean(patientService.getAllAllergyForPatient(username));
        return ResponseEntity.ok(allergyDtoList);
    }

    @GetMapping(value = "getLoyaltyProgram/{username}")
    public ResponseEntity<LoyaltyProgramDto> getLoyaltyProgramForPatient(@PathVariable String username) {
        LoyaltyProgramDto loyaltyProgramDto = loyaltyProgramMapper.entity2Bean(patientService.getLoyaltyProgramForPatient(username));
        return ResponseEntity.ok(loyaltyProgramDto);
    }

    @GetMapping(value = "getAllScheduledAppointment")
    public ResponseEntity<Collection<ExaminationDto>> getAllScheduledAppointmentForPatient(){
        Collection<ExaminationDto> examinationsDto = examinationMapper.entity2Bean(examinationService.getAllScheduledAppointmentForPatient());
        return ResponseEntity.ok(examinationsDto);
    }



}
