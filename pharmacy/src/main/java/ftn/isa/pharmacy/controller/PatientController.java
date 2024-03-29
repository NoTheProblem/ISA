package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.*;
import ftn.isa.pharmacy.mapper.*;
import ftn.isa.pharmacy.mapper.impl.*;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.service.CounselingService;
import ftn.isa.pharmacy.service.ExaminationService;
import ftn.isa.pharmacy.service.PatientService;
import ftn.isa.pharmacy.service.ReservationService;
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
    private final CounselingMapperImpl counselingMapper;
    private final CounselingService counselingService;
    private final ReservationMapperImpl reservationMapper;
    private final ReservationService reservationService;
    private final DermatologistMapper dermatologistMapper;
    private final PharmacistMapper pharmacistMapper;
    private final EvaluationMapper evaluationMapper;
    private final EPrescriptionMapper ePrescriptionMapper;

    @Autowired
    public PatientController(EPrescriptionMapper ePrescriptionMapper, EvaluationMapper evaluationMapper, PharmacistMapper pharmacistMapper, ReservationService reservationService, ReservationMapperImpl reservationMapper, CounselingService counselingService, CounselingMapperImpl counselingMapper, PatientService patientService, PatientMapperImpl patientMapper, MedicineMapperImpl medicineMapper, LoyaltyProgramMapperImpl loyaltyProgramMapper,
                             ExaminationMapperImpl examinationMapper, ExaminationService examinationService, DermatologistMapper dermatologistMapper    ) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.medicineMapper = medicineMapper;
        this.loyaltyProgramMapper = loyaltyProgramMapper;
        this.examinationMapper = examinationMapper;
        this.examinationService  = examinationService;
        this.counselingMapper = counselingMapper;
        this.counselingService = counselingService;
        this.reservationMapper = reservationMapper;
        this.reservationService = reservationService;
        this.dermatologistMapper = dermatologistMapper;
        this.pharmacistMapper = pharmacistMapper;
        this.evaluationMapper = evaluationMapper;
        this.ePrescriptionMapper = ePrescriptionMapper;
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
    public boolean cancelExamination(@RequestBody ExaminationDto examinationDto) {
        System.out.println(111);
        return patientService.cancelExamination(examinationDto);
    }

    @PostMapping("/addCounseling")
    public void addCounseling(@RequestBody CounselingDTO counselingDto) {
        System.out.println("Kontroler");
        patientService.addCounseling(counselingDto);
    }

    @PostMapping("/cancelCounseling")
    public void cancelCounseling(@RequestBody CounselingDTO counselingDto) {
        patientService.cancelCounseling(counselingDto);
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

    @GetMapping(value = "getAllScheduledAppointmentPha")
    public ResponseEntity<Collection<CounselingDTO>> getAllScheduledAppointmentPhaForPatient(){
        Collection<CounselingDTO> counselingsDto = counselingMapper.entity2Bean(counselingService.getAllScheduledAppointmentForPatient());
        return ResponseEntity.ok(counselingsDto);
    }
    @PostMapping("/reserveMedicine")
    public void addReservation(@RequestBody ReservationDTO reservationDTO) {
        System.out.println("Kontroler");
        System.out.println(reservationDTO);
        patientService.addReservation(reservationDTO);
    }

    @GetMapping(value = "getAllReservedMedicine")
    public ResponseEntity<Collection<ReservationDTO>> getAllReservedMedicineForPatient(){
        Collection<ReservationDTO> reservationDTOS = reservationMapper.entity2Bean(reservationService.getAllReservedMedicineForPatient());
        return ResponseEntity.ok(reservationDTOS);
    }

    @PostMapping("/cancelReservation")
    public void cancelReservation(@RequestBody ReservationDTO reservationDto) {
        patientService.cancelReservation(reservationDto);
    }

    @GetMapping(value = "getAllHistoryDerma")
    public ResponseEntity<Collection<ExaminationDto>> getAllHistoryDerma(){
        Collection<ExaminationDto> examinationsDto = examinationMapper.entity2Bean(examinationService.getAllHistoryDerma());
        return ResponseEntity.ok(examinationsDto);
    }

    @GetMapping(value = "getAllHistoryPha")
    public ResponseEntity<Collection<CounselingDTO>> getAllHistoryPha(){
        Collection<CounselingDTO> counselingDtos = counselingMapper.entity2Bean(counselingService.getAllHistoryPha());
        return ResponseEntity.ok(counselingDtos);
    }

    @GetMapping(value = "getAllHistoryDermaForEvaluation")
    public ResponseEntity<Collection<DermatologistDto>> getAllHistoryDermaForEvaluation(){
        Collection<DermatologistDto> dermatologistsDto = dermatologistMapper.entity2Bean(examinationService.getAllHistoryDermaForEvaluation());
        return ResponseEntity.ok(dermatologistsDto);
    }

    @GetMapping(value = "getAllHistoryPhaForEvaluation")
    public ResponseEntity<Collection<PharmacistDTO>> getAllHistoryPhaForEvaluation(){
        Collection<PharmacistDTO> pharmacistDTOS = pharmacistMapper.entity2Bean(counselingService.getAllHistoryPhaForEvaluation());
        return ResponseEntity.ok(pharmacistDTOS);
    }
    @PostMapping("/confirmEvaluation")
    public void confirmEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        System.out.println("Kontroler");
        System.out.println(evaluationDTO);
        patientService.addGrade(evaluationDTO);
    }

    @GetMapping(value = "getAllHistoryEvaluation")
    public ResponseEntity<Collection<EvaluationDTO>> getAllHistoryEvaluation(){
        Collection<EvaluationDTO> evaluationDTOS = evaluationMapper.entity2Bean(patientService.getAllHistoryEvaluation());
        return ResponseEntity.ok(evaluationDTOS);
    }

    @PostMapping("/changeEvaluation")
    public void changeEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        patientService.changeEvaluation(evaluationDTO);
    }

    @GetMapping(value = "getPatient")
    public ResponseEntity<PatientDTO> getPatient(){
        PatientDTO patientDTO = patientMapper.entity2Bean(patientService.getPatient());
        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping(value = "getAllERecepts")
    public ResponseEntity<Collection<EPrescriptionDTO>> getAllRecepts(){
        Collection<EPrescriptionDTO> ePrescriptionDTO = ePrescriptionMapper.entity2Bean(patientService.getAllRecepts());
        return ResponseEntity.ok(ePrescriptionDTO);
    }




}
