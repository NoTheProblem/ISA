package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.MedicineRegisterDto;
import ftn.isa.pharmacy.dto.PromotionDTO;
import ftn.isa.pharmacy.mapper.MedicineRegisterMapper;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.MedicineRepository;
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

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final SysAdminRepository sysAdminRepository;
    private final MedicineRegisterMapper medicineRegisterMapper;


    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, SysAdminRepository sysAdminRepository, MedicineRegisterMapper medicineRegisterMapper) {
        this.medicineRepository = medicineRepository;
        this.sysAdminRepository = sysAdminRepository;
        this.medicineRegisterMapper = medicineRegisterMapper;
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
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
