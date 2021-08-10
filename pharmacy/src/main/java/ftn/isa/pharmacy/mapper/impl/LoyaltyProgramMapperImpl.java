package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.LoyaltyProgramDto;
import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.LoyaltyProgramMapper;
import ftn.isa.pharmacy.mapper.MedicineMapper;
import ftn.isa.pharmacy.model.LoyaltyProgram;
import ftn.isa.pharmacy.model.Medicine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LoyaltyProgramMapperImpl extends AbstractMapper<LoyaltyProgram, LoyaltyProgramDto> implements LoyaltyProgramMapper {


    @Override
    public LoyaltyProgramDto entity2Bean(LoyaltyProgram entity) {
        LoyaltyProgramDto bean = new LoyaltyProgramDto();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public LoyaltyProgram bean2Entity(LoyaltyProgramDto bean) {
        LoyaltyProgram entity = new LoyaltyProgram();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}
