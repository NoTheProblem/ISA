package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.mapper.impl.PharmacistMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PharmacyMapperImpl;
import ftn.isa.pharmacy.model.Pharmacist;
import ftn.isa.pharmacy.repository.PharmacistRepository;
import ftn.isa.pharmacy.service.PharmacistService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final PharmacistMapperImpl pharmacistMapper;
    private final PharmacyMapperImpl pharmacyMapper;

    public PharmacistServiceImpl(PharmacistRepository pharmacistRepository, PharmacistMapperImpl pharmacistMapper, PharmacyMapperImpl pharmacyMapper) {
        this.pharmacistRepository = pharmacistRepository;
        this.pharmacistMapper = pharmacistMapper;
        this.pharmacyMapper = pharmacyMapper;
    }

    @Override
    public Collection<PharmacistDTO> getAll() {
        Collection<Pharmacist> pharmacists = pharmacistRepository.findAll();
        Collection<PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for (Pharmacist pharmacist: pharmacists) {
            PharmacistDTO pharmacistDTO = pharmacistMapper.entity2Bean(pharmacist);
            pharmacistDTO.setPharmacy(pharmacyMapper.entity2Bean(pharmacist.getPharmacy()));
            pharmacistDTOS.add(pharmacistDTO);
        }
        return pharmacistDTOS;
    }
}
