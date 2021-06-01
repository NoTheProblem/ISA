package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.dto.WorkingHoursDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.impl.DermatologistMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PharmacyMapperImpl;
import ftn.isa.pharmacy.mapper.impl.WorkingHoursMapperImpl;
import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Pharmacy;
import ftn.isa.pharmacy.model.PharmacyAdmin;
import ftn.isa.pharmacy.model.User;
import ftn.isa.pharmacy.repository.DermatologistRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.WorkingHoursRepository;
import ftn.isa.pharmacy.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DermatologistServiceImpl implements DermatologistService  {

    private final DermatologistRepository dermatologistRepository;
    private final DermatologistMapperImpl dermatologistMapper;
    private final PharmacyMapperImpl pharmacyMapper;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final WorkingHoursMapperImpl workingHoursMapper;
    private final WorkingHoursRepository workingHoursRepository;

    @Autowired
    public DermatologistServiceImpl(DermatologistRepository dermatologistRepository, DermatologistMapperImpl dermatologistMapper, PharmacyMapperImpl pharmacyMapper, PharmacyAdminRepository pharmacyAdminRepository, WorkingHoursMapperImpl workingHoursMapper, WorkingHoursRepository workingHoursRepository) {
        this.dermatologistRepository = dermatologistRepository;
        this.dermatologistMapper = dermatologistMapper;
        this.pharmacyMapper = pharmacyMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.workingHoursMapper = workingHoursMapper;
        this.workingHoursRepository = workingHoursRepository;
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

    @Override
    public Collection<DermatologistDto> getAllDermatologistsCandidates() {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        Collection<Dermatologist> dermatologists = dermatologistRepository.findAll();
        Collection<Dermatologist> workingDermatologists = pharmacy.getDermatologists();

        dermatologists.removeAll(workingDermatologists);
        Collection<DermatologistDto> dermatologistDtos =  new HashSet<>();
        for (Dermatologist dermatologist:dermatologists) {
            DermatologistDto dermatologistDto = dermatologistMapper.entity2Bean(dermatologist);
            //List<WorkingHoursDTO> workingHoursDTO = (List<WorkingHoursDTO>) workingHoursMapper.entity2Bean(dermatologist.getWorkingHours());
            List<WorkingHoursDTO> workingHoursDTO = (List<WorkingHoursDTO>) workingHoursMapper.entity2Bean(workingHoursRepository.findAllByDermatologist(dermatologist));
            dermatologistDto.setWorkingHours(workingHoursDTO);
            dermatologistDtos.add(dermatologistDto);
        }
        return dermatologistDtos;
    }



    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            return pharmacyAdmin;
        }
        throw new ResourceConflictException(1l,"Ne postoji administrator apoteke!");
    }



}
