package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.model.Pharmacy;

import java.util.Collection;
import java.util.List;

public interface PharmacyService {

    List<Pharmacy> getAll();
    Pharmacy getById(Long id);
    Pharmacy getPharmacyByAdmin();
    Boolean subscribe(Long id);
}
