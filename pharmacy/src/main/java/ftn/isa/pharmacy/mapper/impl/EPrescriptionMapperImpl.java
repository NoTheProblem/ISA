package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.BidDTO;
import ftn.isa.pharmacy.dto.EPrescriptionDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.BidMapper;
import ftn.isa.pharmacy.mapper.EPrescriptionMapper;
import ftn.isa.pharmacy.model.Bid;
import ftn.isa.pharmacy.model.EPrescription;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EPrescriptionMapperImpl extends AbstractMapper<EPrescription, EPrescriptionDTO> implements EPrescriptionMapper {

    @Override
    public EPrescriptionDTO entity2Bean(EPrescription entity) {
        EPrescriptionDTO bean = new EPrescriptionDTO();
        bean.setMedicine(entity.getMedicine().getName());
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public EPrescription bean2Entity(EPrescriptionDTO bean) {
        EPrescription entity = new EPrescription();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}
