package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.IncomeReportDTO;
import ftn.isa.pharmacy.dto.MedicineReportDTO;
import ftn.isa.pharmacy.dto.PharmacyExaminationReportDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.PharmacyReportService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PharmacyReportServiceImpl implements PharmacyReportService {

    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final ExaminationRepository examinationRepository;
    private final PriceMediceListRepository priceMediceListRepository;
    private final ReservationRepository reservationRepository;
    private final PersListRepository persListRepository;

    public PharmacyReportServiceImpl(PharmacyAdminRepository pharmacyAdminRepository, ExaminationRepository examinationRepository, PriceMediceListRepository priceMediceListRepository, ReservationRepository reservationRepository, PersListRepository persListRepository) {
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.examinationRepository = examinationRepository;
        this.priceMediceListRepository = priceMediceListRepository;
        this.reservationRepository = reservationRepository;
        this.persListRepository = persListRepository;
    }


    @Override
    public IncomeReportDTO incomeReport(Date startDate, Date endDate) {
        return null;
    }


    @Override
    public MedicineReportDTO quartallyMedicine(int year, int quartal) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        return null;
    }

    @Override
    public MedicineReportDTO yearlyMedicine(int year) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        return null;
    }

    @Override
    public MedicineReportDTO monthlyMedicine(int year, int month) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        return null;
    }

    @Override
    public PharmacyExaminationReportDTO quartallyExamination(int year, int quartal) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        Date startDate = new Date();
        Date endDate = new Date();
        List<Examination> examinations = examinationRepository.findAllByPharmacyAndDateBefore(pharmacy,startDate,endDate);
        return null;
    }

    @Override
    public PharmacyExaminationReportDTO yearlyExamination(int year) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        Date startDate = new Date();
        Date endDate = new Date();
        List<Examination> examinations = examinationRepository.findAllByPharmacyAndDateBefore(pharmacy,startDate,endDate);
        return null;
    }

    @Override
    public PharmacyExaminationReportDTO monthlyExamination(int year, int month) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        Date startDate = new Date();
        Date endDate = new Date();
        List<Examination> examinations = examinationRepository.findAllByPharmacyAndDateBefore(pharmacy,startDate,endDate);
        return null;
    }



    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            return pharmacyAdmin;
        }
        throw new ResourceConflictException(1l,"Ne postoji administrator apoteke!");
    }



}
