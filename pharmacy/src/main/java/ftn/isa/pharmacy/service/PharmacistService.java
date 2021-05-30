package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.model.Pharmacist;

import java.util.Collection;

public interface PharmacistService {
    Collection<PharmacistDTO> getAll();
}
