package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.mapper.impl.ExaminationMapperImpl;
import ftn.isa.pharmacy.mapper.impl.MedicineMapperImpl;
import ftn.isa.pharmacy.service.ExaminationService;
import ftn.isa.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/examination")
public class ExaminationController {

    private final ExaminationService examinationService;
    private final ExaminationMapperImpl examinationMapper;

    @Autowired
    public ExaminationController(ExaminationService examinationService, ExaminationMapperImpl examinationMapper) {
        this.examinationService = examinationService;
        this.examinationMapper = examinationMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<ExaminationDto>> getAll() {
        Collection<ExaminationDto> examinationDtoList = examinationMapper.entity2Bean(examinationService.getAll());
        return ResponseEntity.ok(examinationDtoList);
    }

}
