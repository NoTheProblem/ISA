package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.IncomeReportDTO;
import ftn.isa.pharmacy.dto.MedicineReportDTO;
import ftn.isa.pharmacy.dto.PharmacyExaminationReportDTO;

import java.util.Date;

public interface PharmacyReportService {

    MedicineReportDTO yearlyMedicine(int year);

    MedicineReportDTO quartallyMedicine(int year, int quartal);

    MedicineReportDTO monthlyMedicine(int year, int month);

    PharmacyExaminationReportDTO yearlyExamination(int year);

    PharmacyExaminationReportDTO quartallyExamination(int year, int quartal);

    PharmacyExaminationReportDTO monthlyExamination(int year, int month);

    IncomeReportDTO incomeReport(Date startDate, Date endDate);
}
