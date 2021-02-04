package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.repository.MedicineRepository;
import ftn.isa.pharmacy.repository.PharmacyRepository;
import ftn.isa.pharmacy.service.MedicineService;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;


    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }
}
