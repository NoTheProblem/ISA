package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.CounselingDTO;
import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.CounselingMapper;
import ftn.isa.pharmacy.mapper.ExaminationMapper;
import ftn.isa.pharmacy.model.Counseling;
import ftn.isa.pharmacy.model.Examination;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CounselingMapperImpl  extends AbstractMapper<Counseling, CounselingDTO> implements CounselingMapper {

    @Override
    public CounselingDTO entity2Bean(Counseling entity) {
        CounselingDTO bean = new CounselingDTO();
        BeanUtils.copyProperties(entity, bean);
        bean.setPharmacistId(entity.getPharmacist().getId());
        bean.setPharmacistName(entity.getPharmacist().getFirstName());
        bean.setPharmacistLastName(entity.getPharmacist().getLastName());
        bean.setPharmacistEvaluationGrade(entity.getPharmacist().getEvaluationGrade());
        bean.setDate(entity.getDate());
        return bean;
    }

    @Override
    public Counseling bean2Entity(CounselingDTO bean) {
        Counseling entity = new Counseling();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }


}
