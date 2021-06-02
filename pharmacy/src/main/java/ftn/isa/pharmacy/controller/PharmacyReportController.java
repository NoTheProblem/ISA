package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.*;
import ftn.isa.pharmacy.service.PharmacyReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/pharmacyReport")
public class PharmacyReportController {

    private final PharmacyReportService pharmacyReportService;

    public PharmacyReportController(PharmacyReportService pharmacyReportService) {
        this.pharmacyReportService = pharmacyReportService;
    }

    @GetMapping(value = "/examination/monthly")
    public ResponseEntity<PharmacyExaminationReportDTO> monthlyExamination(@RequestBody ReportPeriod month) {
        PharmacyExaminationReportDTO pharmacyExaminationReportDTO =  pharmacyReportService.monthlyExamination(month.year, month.period);
        return ResponseEntity.ok(pharmacyExaminationReportDTO);
    }

    @GetMapping(value = "/examination/yearly")
    public ResponseEntity<PharmacyExaminationReportDTO> yearlyExamination(@RequestBody ReportPeriod year) {
        PharmacyExaminationReportDTO pharmacyExaminationReportDTO =  pharmacyReportService.yearlyExamination(year.year);
        return ResponseEntity.ok(pharmacyExaminationReportDTO);
    }

    @GetMapping(value = "/examination/quartally")
    public ResponseEntity<PharmacyExaminationReportDTO> quartallyExamination(@RequestBody ReportPeriod quartal) {
        PharmacyExaminationReportDTO pharmacyExaminationReportDTO =  pharmacyReportService.quartallyExamination(quartal.year,quartal.period);
        return ResponseEntity.ok(pharmacyExaminationReportDTO);
    }

    @GetMapping(value = "/medicine/monthly")
    public ResponseEntity<MedicineReportDTO> monthlyMedicine(@RequestBody ReportPeriod month) {
        MedicineReportDTO medicineReportDTO =  pharmacyReportService.monthlyMedicine(month.year, month.period);
        return ResponseEntity.ok(medicineReportDTO);
    }

    @GetMapping(value = "/medicine/yearly}")
    public ResponseEntity<MedicineReportDTO> yearlyMedicine(@RequestBody ReportPeriod year) {
        MedicineReportDTO medicineReportDTO =  pharmacyReportService.yearlyMedicine(year.year);
        return ResponseEntity.ok(medicineReportDTO);

    }

    @GetMapping(value = "/medicine/quartally")
    public ResponseEntity<MedicineReportDTO> quartallyMedicine(@RequestBody ReportPeriod quartal) {
        MedicineReportDTO medicineReportDTO =  pharmacyReportService.quartallyMedicine(quartal.year, quartal.period);
        return ResponseEntity.ok(medicineReportDTO);
    }

    @GetMapping(value = "/income")
    public ResponseEntity<IncomeReportDTO> incomeReport(@RequestBody TimePeriod timePeriod) {
        IncomeReportDTO incomeReportDTO =  pharmacyReportService.incomeReport(timePeriod.startDate,timePeriod.endDate);
        return ResponseEntity.ok(incomeReportDTO);
    }

    static class TimePeriod {
        private Date startDate;
        private Date endDate;
    }

    static class ReportPeriod {
        private int year;
        private int period;
    }




}
