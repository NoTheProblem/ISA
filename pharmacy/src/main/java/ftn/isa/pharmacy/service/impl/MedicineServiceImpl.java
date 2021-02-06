package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.MedicineQuantityPharmacy;
import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.repository.MedicineQuantityPharmacyRepository;
import ftn.isa.pharmacy.repository.MedicineRepository;
import ftn.isa.pharmacy.repository.PharmacyRepository;
import ftn.isa.pharmacy.service.MedicineService;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository;


    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository) {
        this.medicineRepository = medicineRepository;
        this.medicineQuantityPharmacyRepository = medicineQuantityPharmacyRepository;
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public List<Medicine> getAllAvailable() {
        List<Medicine> listMedicine= medicineRepository.findAll();
        List<Medicine> listMedicine2= medicineRepository.findAllByCode("");
        List<MedicineQuantityPharmacy> listMqp = medicineQuantityPharmacyRepository.findAll();
        for(Medicine medicine: listMedicine){
            for(MedicineQuantityPharmacy mqp: listMqp) {
                if (medicine.getId() == mqp.getMedicine().getId() && mqp.getQuantity() >= 0 ) {
                    listMedicine2.add(medicine);
                    System.out.println(medicine);
                }
            }
        }


        return listMedicine2;
    }
}
