package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.Counseling;
import ftn.isa.pharmacy.model.Examination;
import ftn.isa.pharmacy.repository.CounselingRepository;
import ftn.isa.pharmacy.repository.ExaminationRepository;
import ftn.isa.pharmacy.service.CounselingService;
import ftn.isa.pharmacy.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounselingServiceImpl implements CounselingService {

    private final CounselingRepository counselingRepository;


    @Autowired
    public CounselingServiceImpl(CounselingRepository counselingRepository) {
        this.counselingRepository = counselingRepository;
    }

    @Override
    public List<Counseling> getAll() {
        return counselingRepository.findAll();
    }
}

