package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.AbsenceDTO;
import ftn.isa.pharmacy.mapper.impl.AbsenceMapperImpl;
import ftn.isa.pharmacy.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/absence")
public class AbsenceController {

    private final AbsenceService absenceService;
    private final AbsenceMapperImpl absenceMapper;

    @Autowired
    public AbsenceController(AbsenceService absenceService, AbsenceMapperImpl absenceMapper) {
        this.absenceService = absenceService;
        this.absenceMapper = absenceMapper;
    }


    @GetMapping(value = "/getAllDermatologistRequests")
    public ResponseEntity<Collection<AbsenceDTO>> getAllDermatologistRequests() {
        Collection<AbsenceDTO> absenceDTOCollection = absenceMapper.entity2Bean(absenceService.getAllDermatologistRequests());
        return ResponseEntity.ok(absenceDTOCollection);
    }


    @GetMapping(value = "/getAllPharmacistRequests")
    public ResponseEntity<Collection<AbsenceDTO>> getAllPharmacistRequests() {
        Collection<AbsenceDTO> absenceDTOCollection = absenceMapper.entity2Bean(absenceService.getAllPharmacistRequests());
        return ResponseEntity.ok(absenceDTOCollection);
    }

    @PostMapping("/acceptPha")
    public void acceptAbsence(@RequestBody AbsenceDTO absenceDTO) {
        absenceService.acceptAbsencePha(absenceDTO);
    }

    @PostMapping("/declinePha")
    public void declineAbsence(@RequestBody AbsenceDTO absenceDTO) {
        absenceService.declineAbsencePha(absenceDTO);
    }



}
