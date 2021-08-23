package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.config.MailConfig;
import ftn.isa.pharmacy.dto.*;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.*;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.PatientService;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    final private MailConfig mailConfig;
    private final MedicineMapper medicineMapper;
    private final ExaminationMapper examinationMapper;
    private final PatientRepository patientRepository;
    private final ExaminationRepository examinationRepository;
    private final LoyaltyProgramRepository loyaltyProgramRepository;
    private final CounselingMapper counselingMapper;
    private final CounselingRepository counselingRepository;
    private final PharmacistRepository pharmacistRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository;
    private final EvaluationMapper evaluationMapper;
    private final EvaluationRepository evaluationRepository;
    private final DermatologistRepository dermatologistRepository;

    @Autowired
    public PatientServiceImpl(DermatologistRepository dermatologistRepository, EvaluationRepository evaluationRepository, EvaluationMapper evaluationMapper, MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository, MedicineRepository medicineRepository, PharmacyRepository pharmacyRepository, ReservationMapper reservationMapper, ReservationRepository reservationRepository,PharmacistRepository pharmacistRepository, CounselingRepository counselingRepository, CounselingMapper counselingMapper, MailConfig mailConfig, MedicineMapper medicineMapper, PatientRepository patientRepository, LoyaltyProgramRepository loyaltyProgramRepository, ExaminationMapper examinationMapper, ExaminationRepository examinationRepository) {
        this.medicineMapper = medicineMapper;
        this.patientRepository = patientRepository;
        this.loyaltyProgramRepository = loyaltyProgramRepository;
        this.examinationMapper = examinationMapper;
        this.examinationRepository = examinationRepository;
        this.mailConfig = mailConfig;
        this.counselingMapper = counselingMapper;
        this.counselingRepository = counselingRepository;
        this.pharmacistRepository = pharmacistRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
        this.medicineQuantityPharmacyRepository = medicineQuantityPharmacyRepository;
        this.evaluationMapper = evaluationMapper;
        this.evaluationRepository = evaluationRepository;
        this.dermatologistRepository = dermatologistRepository;
    }

    private JavaMailSenderImpl getJMS(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.mailConfig.getHost());
        mailSender.setPort(this.mailConfig.getPort());
        mailSender.setUsername(this.mailConfig.getUsername());
        mailSender.setPassword(this.mailConfig.getPassword());
        return mailSender;
    }

    @Override
    public void addAllergy(MedicineDto medicineDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Patient patient = (Patient) authentication.getPrincipal(); --> ovo radi, ali ne povuce allergicMedicines jer je lazy
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Medicine medicine = medicineMapper.bean2Entity(medicineDto);
            Patient patient = patientOptional.get();
            patient.getAllergicMedicines().add(medicine);
            patientRepository.saveAndFlush(patient);
        }
    }

    @Override
    public void addExamination(ExaminationDto examinationDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Patient patient = (Patient) authentication.getPrincipal(); --> ovo radi, ali ne povuce allergicMedicines jer je lazy
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Examination examination = examinationMapper.bean2Entity(examinationDto);
            Optional<Examination> examination1 = examinationRepository.findById(examinationDto.getId());
            examination = examination1.get();

            Patient patient = patientOptional.get();
            examination.setPatient(patient);
            examination.setFree(false);
            examination.setPenalty(true);

            JavaMailSenderImpl mailSender = getJMS();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("apoteka@gmail.com");
            mailMessage.setTo(patient.getEmail());
            mailMessage.setSubject("Zakazivanje termina");
            mailMessage.setText("Postovani " + patient.getFirstName() + ",\n"+ "\n"+
                    "Uspesno ste zakazali termin za " + examination.getDate() +
                    " kod lekara" + examination.getDermatologist().getFirstName() + "  " + examination.getDermatologist().getLastName() +  "\n"+ "\n"+
                    "Pozdrav," + "\n"+
                    "AP tim");
            mailSender.send(mailMessage);

            examinationRepository.saveAndFlush(examination);
        }
    }

    @Override
    public boolean cancelExamination(ExaminationDto examinationDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Patient patient = (Patient) authentication.getPrincipal(); --> ovo radi, ali ne povuce allergicMedicines jer je lazy
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Examination examination = examinationMapper.bean2Entity(examinationDto);
            Optional<Examination> examination1 = examinationRepository.findById(examinationDto.getId());
            examination = examination1.get();

            Patient patient = patientOptional.get();
            long helper;
            helper = examination.getDate().getTime();
            System.out.println(helper);
            System.out.println(System.currentTimeMillis());
            if ((helper - 86400000) > System.currentTimeMillis()) {
                examination.setPatient(null);
                examination.setFree(true);
                System.out.println(System.currentTimeMillis());
                examinationRepository.saveAndFlush(examination);
                return true;
            }



            //examinationRepository.saveAndFlush(examination);
        }
        return false;
    }

    @Override
    public Collection<Patient> getAll() {
        return patientRepository.findAll();
    }


    @Override
    public Collection<Medicine> getAllAllergyForPatient(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        System.out.println(((User) authentication.getPrincipal()).getId());
        Patient patient = patientOptional.get();
        return patient.getAllergicMedicines();

    }

    @Override
    public LoyaltyProgram getLoyaltyProgramForPatient(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        System.out.println(((User) authentication.getPrincipal()).getId());
        Patient patient = patientOptional.get();
        LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findByLoyaltyScore(patient.getLoyaltyScore());

        System.out.println(loyaltyProgram.getId());
        patient.setLoyaltyProgram(loyaltyProgram);
        patientRepository.saveAndFlush(patient);
        loyaltyProgram.getPatients().add(patient);
        loyaltyProgramRepository.saveAndFlush(loyaltyProgram);
        return patient.getLoyaltyProgram();

    }

    @Override
    public void addCounseling(CounselingDTO counselingDto){
        System.out.println("Servis");
        Counseling counseling = counselingMapper.bean2Entity(counselingDto);
        System.out.println("Maper");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        System.out.println(((User) authentication.getPrincipal()).getId());
        Patient patient = patientOptional.get();
        counseling.setPatient(patient);
        counseling.setPharmacist(pharmacistRepository.getOne(counselingDto.getPharmacistId()));
        System.out.println(patient);
        System.out.println(counseling);
        int hours = Integer.parseInt(counselingDto.getTime().substring(0,2));
        int minutes = Integer.parseInt(counselingDto.getTime().substring(3));
        Date dateAddTime = counselingDto.getDate();
        dateAddTime.setHours(hours);
        dateAddTime.setMinutes(minutes);
        counseling.setDate(dateAddTime);
        counseling.setDurationMinutes(30);

        Date startDate = new Date();
        startDate.setTime(dateAddTime.getTime());
        startDate.setHours(1);
        Date nextDay =  new Date();
        nextDay.setTime(dateAddTime.getTime());
        nextDay.setHours(23);
        Date endDate = dateAddTime;
        endDate.setMinutes(dateAddTime.getMinutes()+counseling.getDurationMinutes());

        Collection<Counseling> counselingsOnThatDay = counselingRepository.findAllByDateBetween(startDate, nextDay);
        for (Counseling exa: counselingsOnThatDay) {
            Date exaDate = exa.getDate();
            Date exaStart =  new Date();
            exaStart.setTime(exaDate.getTime());
            Date exaEnd =  new Date();
            exaEnd.setTime(exaDate.getTime());
            exaEnd.setMinutes(exa.getDate().getHours()+ exa.getDurationMinutes());
            if (dateAddTime.after(exaStart) && dateAddTime.before(exaEnd)){
                throw new ResourceConflictException(1L,"Preklapa se sa terminom!");
            }
            if(endDate.after(exaStart) && endDate.before(exaEnd)){
                throw new ResourceConflictException(1L,"Preklapa se sa terminom!");
            }
        }

        JavaMailSenderImpl mailSender = getJMS();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("apoteka@gmail.com");
        mailMessage.setTo(patient.getEmail());
        mailMessage.setSubject("Zakazivanje termina");
        mailMessage.setText("Postovani " + patient.getFirstName() + ",\n"+ "\n"+
                "Uspesno ste zakazali termin za farmaceuta " + counseling.getDate() +
                " kod lekara" + counseling.getPharmacist().getFirstName() + "  " + counseling.getPharmacist().getLastName() +  "\n"+ "\n"+
                "Pozdrav," + "\n"+
                "AP tim");
        mailSender.send(mailMessage);

        counselingRepository.saveAndFlush(counseling);


    }

    @Override
    public boolean cancelCounseling(CounselingDTO counselingDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Patient patient = (Patient) authentication.getPrincipal(); --> ovo radi, ali ne povuce allergicMedicines jer je lazy
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Counseling counseling = counselingMapper.bean2Entity(counselingDto);
            Optional<Counseling> counseling1 = counselingRepository.findById(counselingDto.getId());
            counseling = counseling1.get();

            Patient patient = patientOptional.get();
            long helper;
            helper = counseling.getDate().getTime();
            System.out.println(helper);
            System.out.println(System.currentTimeMillis());
            if ((helper - 86400000) > System.currentTimeMillis()) {
                counseling.setPatient(null);
                counseling.setFree(true);
                System.out.println(System.currentTimeMillis());
                counselingRepository.saveAndFlush(counseling);
                return true;
            }



            //examinationRepository.saveAndFlush(examination);
        }
        return false;
    }

    @Override
    public void addReservation(ReservationDTO reservationDto){
        Reservation reservation = reservationMapper.bean2Entity(reservationDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Optional<Pharmacy> pharmacy= pharmacyRepository.findById((long) reservationDto.getPharmacyid());
            reservation.setPharmacy(pharmacy.get());
            Optional<Medicine> medicine= medicineRepository.findById((long) reservationDto.getMedicineid());
            reservation.setMedicine(medicine.get());
            reservation.setEndDate(reservationDto.getEndDate());
            reservation.setPickUpTime(reservationDto.getPickedUpTime());
            reservation.setEndTime(reservationDto.getEndTime());
            reservation.setPatient(patientOptional.get());
            Patient patient = patientOptional.get();

            reservationRepository.saveAndFlush(reservation);

            Reservation reservation1 = reservationRepository.findByPharmacyAndMedicineAndPatient(pharmacy.get(),medicine.get(),patient);

            JavaMailSenderImpl mailSender = getJMS();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("apoteka@gmail.com");
            mailMessage.setTo(patient.getEmail());
            mailMessage.setSubject("Rezervisanje leka");
            mailMessage.setText("Postovani " + patient.getFirstName() + ",\n"+ "\n"+
                    "Uspesno ste rezervisali lek " + reservation.getMedicine().getName() +
                    " broj rezervacije" + reservation1.getId() +  "\n"+ "\n"+
                    "Pozdrav," + "\n"+
                    "AP tim");
            mailSender.send(mailMessage);

            MedicineQuantityPharmacy mqp = medicineQuantityPharmacyRepository.findAllByPharmacyAndMedicine(pharmacy.get(),medicine.get());
            mqp.setQuantity(mqp.getQuantity() - 1);

            medicineQuantityPharmacyRepository.saveAndFlush(mqp);



        }




    }

    @Override
    public boolean cancelReservation(ReservationDTO reservationDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Patient patient = (Patient) authentication.getPrincipal(); --> ovo radi, ali ne povuce allergicMedicines jer je lazy
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Reservation reservation = reservationMapper.bean2Entity(reservationDto);
            Optional<Reservation> reservation1 = reservationRepository.findById(reservationDto.getId());
            reservation = reservation1.get();

            Patient patient = patientOptional.get();
            long helper;
            helper = reservation.getEndDate().getTime();
            System.out.println(helper);
            System.out.println(System.currentTimeMillis());
            if ((helper - 86400000) > System.currentTimeMillis()) {


                MedicineQuantityPharmacy mqp = medicineQuantityPharmacyRepository.findAllByPharmacyAndMedicine(reservation.getPharmacy(),reservation.getMedicine());
                mqp.setQuantity(mqp.getQuantity() + 1);

                medicineQuantityPharmacyRepository.saveAndFlush(mqp);
                reservation.setPatient(null);
                reservationRepository.saveAndFlush(reservation);

                return true;
            }



            //examinationRepository.saveAndFlush(examination);
        }
        return false;
    }

    @Override
    public void addGrade(EvaluationDTO evaluationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Evaluation evaluation = evaluationMapper.bean2Entity(evaluationDTO);
            evaluation.setValid(true);
            evaluation.setPatient(patientOptional.get());
            evaluation.setGrade(evaluationDTO.getGrade());
            Patient patient = patientOptional.get();

            Optional<Dermatologist> dermatologist = dermatologistRepository.findById(evaluationDTO.getIdOfEvaluated());
            System.out.println(dermatologist.isPresent());
            Optional<Pharmacist> pharmacist = pharmacistRepository.findById(evaluationDTO.getIdOfEvaluated());
            System.out.println(pharmacist.isPresent());
            Optional<Medicine> medicine = medicineRepository.findById(evaluationDTO.getIdOfEvaluated());
            System.out.println(pharmacist.isPresent());
            Optional<Pharmacy> pharmacy = pharmacyRepository.findById(evaluationDTO.getIdOfEvaluated());
            System.out.println(pharmacy.isPresent());
            if(evaluation.getTypeOfEvaluation().equals("farmaceut")){
                Optional<Pharmacist> pharmacistChoosen = pharmacistRepository.findById(evaluationDTO.getIdOfEvaluated());
                evaluation.setName(pharmacistChoosen.get().getFirstName() + ' ' + pharmacistChoosen.get().getLastName());
            }
            if(evaluation.getTypeOfEvaluation().equals("dermatolog")){
                Optional<Dermatologist> dermatologistChoosen = dermatologistRepository.findById(evaluationDTO.getIdOfEvaluated());
                evaluation.setName(dermatologistChoosen.get().getFirstName() + ' ' + dermatologistChoosen.get().getLastName());
            }
            if(evaluation.getTypeOfEvaluation().equals("lek")){
                Optional<Medicine> medicineChoosen = medicineRepository.findById(evaluationDTO.getIdOfEvaluated());
                evaluation.setName(medicineChoosen.get().getName());
            }
            if(evaluation.getTypeOfEvaluation().equals("apoteka")){
                Optional<Pharmacy> pharmacyChoosen = pharmacyRepository.findById(evaluationDTO.getIdOfEvaluated());
                evaluation.setName(pharmacyChoosen.get().getName());
            }
            evaluationRepository.saveAndFlush(evaluation);
            //List<Evaluation> evaluations = evaluationRepository.findByIdOfEvaluatedAndValid(evaluationDTO.getIdOfEvaluated(), true);
            if ((pharmacist.isPresent()) & evaluation.getTypeOfEvaluation().equals("farmaceut")) {
                Set<Evaluation> evaluations = evaluationRepository.findByIdOfEvaluatedAndValidAndTypeOfEvaluation(evaluationDTO.getIdOfEvaluated(), true, "farmaceut");
                float new_grade = 0;
                for (Evaluation eva : evaluations) {
                    new_grade = new_grade + eva.getGrade();
                }
                new_grade = new_grade / evaluations.size();
                Pharmacist pharmacist_help = new Pharmacist();
                pharmacist_help  = pharmacist.get();
                pharmacist_help.setEvaluationGrade(new_grade);
                pharmacistRepository.saveAndFlush(pharmacist_help);
            }
            if (dermatologist.isPresent() & evaluation.getTypeOfEvaluation().equals("dermatolog")) {
                Set<Evaluation> evaluations = evaluationRepository.findByIdOfEvaluatedAndValidAndTypeOfEvaluation(evaluationDTO.getIdOfEvaluated(), true, "dermatolog");
                float new_grade = 0;
                for (Evaluation eva : evaluations) {
                    new_grade = new_grade + eva.getGrade();
                }
                new_grade = new_grade / evaluations.size();
                dermatologist.get().setEvaluationGrade(new_grade);
                Dermatologist dermatologist_help = new Dermatologist();
                dermatologist_help  = dermatologist.get();
                dermatologist_help.setEvaluationGrade(new_grade);
                dermatologistRepository.saveAndFlush(dermatologist_help);
            }
            if (medicine.isPresent() & evaluation.getTypeOfEvaluation().equals("lek")){
                Set<Evaluation> evaluations = evaluationRepository.findByIdOfEvaluatedAndValidAndTypeOfEvaluation(evaluationDTO.getIdOfEvaluated(), true, "lek");
                float new_grade = 0;
                for (Evaluation eva : evaluations) {
                    new_grade = new_grade + eva.getGrade();
                }
                new_grade = new_grade / evaluations.size();
                System.out.println(evaluations.size());
                medicine.get().setEvaluationGrade(new_grade);
                Medicine medicine_help = new Medicine();
                medicine_help  = medicine.get();
                medicine_help.setEvaluationGrade(new_grade);
                medicineRepository.saveAndFlush(medicine_help);
            }
            if (pharmacy.isPresent() & evaluation.getTypeOfEvaluation().equals("apoteka")){
                Set<Evaluation> evaluations = evaluationRepository.findByIdOfEvaluatedAndValidAndTypeOfEvaluation(evaluationDTO.getIdOfEvaluated(), true, "apoteka");
                float new_grade = 0;
                for (Evaluation eva : evaluations) {
                    new_grade = new_grade + eva.getGrade();
                }
                new_grade = new_grade / evaluations.size();
                pharmacy.get().setEvaluationGrade(new_grade);
                Pharmacy pharmacy_help = new Pharmacy();
                pharmacy_help  = pharmacy.get();
                pharmacy_help.setEvaluationGrade(new_grade);
                pharmacyRepository.saveAndFlush(pharmacy_help);
            }




        }



    }


    @Override
    public List<Evaluation> getAllHistoryEvaluation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        System.out.println(((User) authentication.getPrincipal()).getId());
        Patient patient = patientOptional.get();
        List<Evaluation> evaluations = evaluationRepository.findAllByPatient(patient);
        return evaluations;

    }

    @Override
    public void changeEvaluation(EvaluationDTO evaluationDTO){
        Optional<Evaluation> evaluationOpt = evaluationRepository.findById(evaluationDTO.getId());
        if (evaluationOpt.isPresent()){
                Evaluation evaluation = evaluationOpt.get();
                evaluation.setGrade(evaluationDTO.getGrade());
                evaluationRepository.saveAndFlush(evaluation);
                String type = evaluation.getTypeOfEvaluation();
                Long idOfEvaluated = evaluation.getIdOfEvaluated();
                List<Evaluation> evaluations = evaluationRepository.findAllByIdOfEvaluatedAndTypeOfEvaluation(idOfEvaluated,type);
                float new_grade = 0;
                for(Evaluation evaluation1: evaluations){
                    new_grade = new_grade + evaluation1.getGrade();
                }
                new_grade = new_grade / evaluations.size();

                if(type.equals("dermatolog")){
                    Optional<Dermatologist> dermatologistOpt = dermatologistRepository.findById(evaluation.getIdOfEvaluated());
                    Dermatologist dermatologist = dermatologistOpt.get();
                    dermatologist.setEvaluationGrade(new_grade);
                    dermatologistRepository.saveAndFlush(dermatologist);
                }

                if(type.equals("farmaceut")){
                    Optional<Pharmacist> pharmacistOpt = pharmacistRepository.findById(evaluation.getIdOfEvaluated());
                    Pharmacist pharmacist = pharmacistOpt.get();
                    pharmacist.setEvaluationGrade(new_grade);
                    pharmacistRepository.saveAndFlush(pharmacist);
                }

                if(type.equals("lek")){
                    Optional<Medicine> medicineOptional = medicineRepository.findById(evaluation.getIdOfEvaluated());
                    Medicine medicine = medicineOptional.get();
                    medicine.setEvaluationGrade(new_grade);
                    medicineRepository.saveAndFlush(medicine);
                }

                if(type.equals("apoteka")){
                    Optional<Pharmacy> pharmacyOptional = pharmacyRepository.findById(evaluation.getIdOfEvaluated());
                    Pharmacy pharmacy = pharmacyOptional.get();
                    pharmacy.setEvaluationGrade(new_grade);
                    pharmacyRepository.saveAndFlush(pharmacy);
                }

        }
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void Scheduler() {
        Date date = new Date();

        Date day_end = date;
        day_end.setMinutes(0);
        day_end.setHours(0);
        day_end.setSeconds(0);

        Date previous_day = new Date();
        previous_day.setDate(previous_day.getDate()-1);
        Date day_begin = previous_day;
        day_begin.setMinutes(0);
        day_begin.setHours(0);
        day_begin.setSeconds(0);

        System.out.println(day_begin );
        System.out.println(day_end );

        Collection<Counseling> counselings  = counselingRepository.findAllByDateBetweenAndPenalty(day_begin, day_end, false);
        for (Counseling counseling: counselings){
            System.out.println(counseling);
            Patient patient = counseling.getPatient();
            if (patient != null) {
                patient.setPenaltyScore(patient.getPenaltyScore() + 1);
                patientRepository.saveAndFlush(patient);
            }
        }

        Collection<Examination> examinations  = examinationRepository.findAllByDateBetweenAndPenalty(day_begin, day_end, true);
        for (Examination examination: examinations){
            System.out.println(examinations);
            Patient patient = examination.getPatient();
            if (patient != null) {
                patient.setPenaltyScore(patient.getPenaltyScore() + 1);
                patientRepository.saveAndFlush(patient);
            }
        }

        Collection<Reservation> reservations  = reservationRepository.findAllByEndDateBetweenAndPickedUp(day_begin, day_end, false);
        for (Reservation reservation: reservations){
            System.out.println(reservation);
            Patient patient = reservation.getPatient();
            if (patient != null) {
                patient.setPenaltyScore(patient.getPenaltyScore() + 1);
                patientRepository.saveAndFlush(patient);
            }
        }

        return;

    }

    @Override
    public Patient getPatient() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        System.out.println(((User) authentication.getPrincipal()).getId());
        Patient patient = patientOptional.get();
        return patient;
    }

    @Override
    @Scheduled(cron = "0 0 0 1 * *")
    public void PenaltyScheduler() {
        List<Patient> patients = patientRepository.findAll();
        for (Patient patient: patients){
            patient.setPenaltyScore(0);
            patientRepository.saveAndFlush(patient);
        }


    }

}
