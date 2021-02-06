package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.ReservationDto;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.MedicineMapper;
import ftn.isa.pharmacy.mapper.ReservationMapper;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.Reservation;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl extends AbstractMapper<Reservation, ReservationDto> implements ReservationMapper {


    @Override
    public ReservationDto entity2Bean(Reservation entity) {
        ReservationDto bean = new ReservationDto();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }

    @Override
    public Reservation bean2Entity(ReservationDto bean) {
        Reservation entity = new Reservation();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }

}
