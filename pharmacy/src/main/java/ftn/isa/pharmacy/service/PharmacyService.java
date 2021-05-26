package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Pharmacist;
import ftn.isa.pharmacy.model.Pharmacy;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PharmacyService {

    List<Pharmacy> getAll();
    Pharmacy getById(Long id);
    Pharmacy getPharmacyByAdmin();
    Boolean subscribe(Long id);
    Set<Dermatologist> getDermaByPhaID(Long id);
    Set<Pharmacist> getPharmaByPhaID(Long id);
    Collection<DermatologistDto> getDermaForPhaAdmin();

    void addPharmacy(PharmacyDto pharmacyDto);
}
