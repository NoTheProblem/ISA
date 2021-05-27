package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.PriceMediceDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.PriceMediceMapper;
import ftn.isa.pharmacy.mapper.impl.MedicineMapperImpl;
import ftn.isa.pharmacy.repository.MedicineQuantityPharmacyRepository;
import ftn.isa.pharmacy.repository.MedicineRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.PriceMediceListRepository;
import ftn.isa.pharmacy.dto.MedicineRegisterDto;
import ftn.isa.pharmacy.mapper.MedicineRegisterMapper;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.SysAdminRepository;
import ftn.isa.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository;
    private final PriceMediceListRepository priceMediceListRepository;
    private final MedicineMapperImpl medicineMapper;
    private final PriceMediceMapper priceMediceMapper;
  
    private final SysAdminRepository sysAdminRepository;
    private final MedicineRegisterMapper medicineRegisterMapper;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, PharmacyAdminRepository pharmacyAdminRepository, MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository, PriceMediceListRepository priceMediceListRepository, MedicineMapperImpl medicineMapper, PriceMediceMapper priceMediceMapper, SysAdminRepository sysAdminRepository, MedicineRegisterMapper medicineRegisterMapper) {
        this.medicineRepository = medicineRepository;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.medicineQuantityPharmacyRepository = medicineQuantityPharmacyRepository;
        this.priceMediceListRepository = priceMediceListRepository;
        this.medicineMapper = medicineMapper;
        this.priceMediceMapper = priceMediceMapper;
        this.sysAdminRepository = sysAdminRepository;
        this.medicineRegisterMapper = medicineRegisterMapper;
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public List<Medicine> getMedicinesForPhaAdmin() {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        List<MedicineQuantityPharmacy> medicineQuantityPharmacySet = medicineQuantityPharmacyRepository.findAllByPharmacy(pharmacy);
        //Set<MedicineQuantityPharmacy> medicineQuantityPharmacySet = pharmacy.getMedicationQuantities();
        List<Medicine> medicines = new Stack<>();
        for (MedicineQuantityPharmacy medQ: medicineQuantityPharmacySet) {
            medicines.add(medQ.getMedicine());
        }
        return medicines;
    }

    @Override
    public PriceMediceDTO getMedPriceForPhaAdmin(Long medID) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        List<PriceMediceList> priceMediceLists = new Stack<>();
        Medicine medicine = medicineRepository.getOne(medID);
        MedicineDto medicineDto = medicineMapper.entity2Bean(medicine);
        priceMediceLists = priceMediceListRepository.findAllByPharmacyAndMedicineOrderByEndDateDesc(pharmacy,medicine);
        if(priceMediceLists.isEmpty()){
            PriceMediceList priceMediceList = new PriceMediceList();
            PriceMediceDTO priceMediceDTO = priceMediceMapper.entity2Bean(priceMediceList);
            priceMediceDTO.setPrice(-1);
            priceMediceDTO.setMedicine(medicineDto);
            return priceMediceDTO;
        }
        PriceMediceList priceMediceList = priceMediceLists.get(0);
        PriceMediceDTO priceMediceDTO = priceMediceMapper.entity2Bean(priceMediceList);
        priceMediceDTO.setMedicine(medicineDto);
        return priceMediceDTO;
    }

    @Override
    public void addNewMedPrice(PriceMediceDTO priceMediceDTO) {
        Medicine medicine = medicineMapper.bean2Entity(priceMediceDTO.getMedicine());
        PriceMediceList priceMediceList = priceMediceMapper.bean2Entity(priceMediceDTO);
        Date date = new Date();
        System.out.println("Test1");
        System.out.println(priceMediceList.getStartDate());
        System.out.println(date);
        if(priceMediceList.getStartDate().before(date)){
            throw new ResourceConflictException(1l,"Ne moze se unazad stavljati vreme");

        }
        priceMediceList.setMedicine(medicine);
        date.setTime(priceMediceList.getStartDate().getTime());
        Date dateEnd = new Date();
        dateEnd.setTime(priceMediceList.getEndDate().getTime());
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        //Ne radi
        List<PriceMediceList> priceMediceLists = priceMediceListRepository.
                findAllByPharmacyAndMedicineAndStartDateAfterOrEndDateAfter(pharmacy,medicine,priceMediceList.getStartDate(),priceMediceList.getEndDate());
        //TODO test..
        for (PriceMediceList priceMed: priceMediceLists) {
            System.out.println("Test2");
            System.out.println(date);
            System.out.println(dateEnd);
            System.out.println(priceMed.getStartDate());
            System.out.println(priceMed.getEndDate());
            if (date.after(priceMed.getStartDate()) && date.before(priceMed.getEndDate())){
                System.out.println();
                return ;
            }
            if(dateEnd.after(priceMed.getStartDate()) && dateEnd.before(priceMed.getEndDate())){
                System.out.println();
                return ;
            }
        }


        priceMediceList.setPharmacy(pharmacy);
        priceMediceListRepository.save(priceMediceList);
    }

    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            return pharmacyAdminOptional.get();
        }
        return null;
    }
    public List<Medicine> getAllReg() {
        return medicineRepository.findAll();
    }

    @Override
    public void addMedicine(MedicineRegisterDto medicineRegisterDto){
        Medicine medicine = medicineRegisterMapper.bean2Entity(medicineRegisterDto);
        medicineRepository.save(medicine);
    }

    private SysAdmin getSysAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<SysAdmin> sysAdminOptional = sysAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(sysAdminOptional.isPresent()) {
            SysAdmin sysAdmin = sysAdminOptional.get();
            return sysAdmin;
        }
        return null;
    }

}
