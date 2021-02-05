package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.Examination;
import ftn.isa.pharmacy.repository.ExaminationRepository;
import ftn.isa.pharmacy.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationRepository examinationRepository;


    @Autowired
    public ExaminationServiceImpl(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    @Override
    public List<Examination> getAll() {
        return examinationRepository.findAll();
    }
}
