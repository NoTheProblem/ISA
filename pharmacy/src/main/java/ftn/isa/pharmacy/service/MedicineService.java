package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.PriceMediceDTO;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.PriceMediceList;

import java.util.Collection;
import java.util.List;

public interface MedicineService {

    List<Medicine> getAll();

    List<Medicine> getMedicinesForPhaAdmin();

    PriceMediceDTO getMedPriceForPhaAdmin(Long medID);

    void addNewMedPrice(PriceMediceDTO priceMediceDTO);
}
