package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.MedicineRegisterDto;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.Promotion;

import java.util.Collection;
import java.util.List;

public interface MedicineService {

    List<Medicine> getAll();
    List<Medicine> getAllReg();
    void addMedicine(MedicineRegisterDto medicineRegisterDto);
}
