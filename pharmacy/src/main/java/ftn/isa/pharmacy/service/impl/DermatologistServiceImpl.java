package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.mapper.impl.DermatologistMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PharmacyMapperImpl;
import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.repository.DermatologistRepository;
import ftn.isa.pharmacy.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.Stack;

@Service
public class DermatologistServiceImpl implements DermatologistService  {

    private final DermatologistRepository dermatologistRepository;
    private final DermatologistMapperImpl dermatologistMapper;
    private final PharmacyMapperImpl pharmacyMapper;

    @Autowired
    public DermatologistServiceImpl(DermatologistRepository dermatologistRepository, DermatologistMapperImpl dermatologistMapper, PharmacyMapperImpl pharmacyMapper) {
        this.dermatologistRepository = dermatologistRepository;
        this.dermatologistMapper = dermatologistMapper;
        this.pharmacyMapper = pharmacyMapper;
    }

    @Override
    public List<DermatologistDto> getAll() {
        List<Dermatologist> dermatologists = dermatologistRepository.findAll();
        List<DermatologistDto> dermatologistDtos = new Stack<>();
        for (Dermatologist dermatologist: dermatologists) {
            DermatologistDto dermatologistDto = dermatologistMapper.entity2Bean(dermatologist);
            Set<Pharmacy> pharmacySet =  dermatologist.getPharmacys();
            List<PharmacyDto> pharmacyDtos = new Stack<>();
            for (Pharmacy pharmacy:pharmacySet) {
                PharmacyDto pharmacyDto = pharmacyMapper.entity2Bean(pharmacy);
                pharmacyDtos.add(pharmacyDto);
            }
            dermatologistDto.setPharmacys(pharmacyDtos);
            dermatologistDtos.add(dermatologistDto);
        }
        return dermatologistDtos;
    }


}
