package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.AbsenceDTO;
import ftn.isa.pharmacy.mapper.impl.AbsenceMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.AbsenceRequestRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRequestRepository absenceRequestRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final AbsenceMapperImpl absenceMapper;
    private final MailServiceImpl mailService;

    @Autowired
    public AbsenceServiceImpl(AbsenceRequestRepository absenceRequestRepository,
                              PharmacyAdminRepository pharmacyAdminRepository,AbsenceMapperImpl absenceMapper,
                              MailServiceImpl mailService) {
        this.absenceRequestRepository = absenceRequestRepository;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.absenceMapper = absenceMapper;
        this.mailService = mailService;
    }


    @Override
    public Collection<AbsenceRequest> getAllDermatologistRequests() {
        return absenceRequestRepository.getAllDermatologistRequests();
    }

    @Override
    public Collection<AbsenceRequest> getAllPharmacistRequests() {
        return absenceRequestRepository.getAllPharmacistRequests();
    }

    @Override
    public void acceptAbsencePha(AbsenceDTO absenceDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()){
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            AbsenceRequest absenceRequest = absenceMapper.bean2Entity(absenceDTO);
            absenceRequest.setAdminId(pharmacyAdmin.getId());
            absenceRequest.setStatus("Odobreno");
            absenceRequestRepository.saveAndFlush(absenceRequest);
            mailService.absenceAcceptedNotification(absenceRequest);
        }

    }

    @Override
    public void declineAbsencePha(AbsenceDTO absenceDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()){
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            AbsenceRequest absenceRequest = absenceMapper.bean2Entity(absenceDTO);
            absenceRequest.setAdminId(pharmacyAdmin.getId());
            absenceRequest.setStatus("Odbijeno");
            absenceRequestRepository.saveAndFlush(absenceRequest);
            mailService.absenceDeclinedNotification(absenceRequest);
        }

    }
}
