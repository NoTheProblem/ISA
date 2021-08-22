package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.BidDTO;
import ftn.isa.pharmacy.dto.EvaluationDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.BidMapper;
import ftn.isa.pharmacy.mapper.EvaluationMapper;
import ftn.isa.pharmacy.model.Bid;
import ftn.isa.pharmacy.model.Evaluation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EvaluationMapperImpl extends AbstractMapper<Evaluation, EvaluationDTO> implements EvaluationMapper {
    @Override
    public EvaluationDTO entity2Bean(Evaluation entity) {
        EvaluationDTO bean = new EvaluationDTO();
        BeanUtils.copyProperties(entity, bean);

        return bean;
    }

    @Override
    public Evaluation bean2Entity(EvaluationDTO bean) {
        Evaluation entity = new Evaluation();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}
