package ftn.isa.pharmacy.mapper.impl;

import ftn.isa.pharmacy.dto.ReservationDTO;
import ftn.isa.pharmacy.dto.SupplierDTO;
import ftn.isa.pharmacy.mapper.AbstractMapper;
import ftn.isa.pharmacy.mapper.ReservationMapper;
import ftn.isa.pharmacy.mapper.SupplierMapper;
import ftn.isa.pharmacy.model.Reservation;
import ftn.isa.pharmacy.model.Supplier;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl extends AbstractMapper<Reservation, ReservationDTO> implements ReservationMapper {

    @Override
    public ReservationDTO entity2Bean(Reservation entity) {
        ReservationDTO bean = new ReservationDTO();

        BeanUtils.copyProperties(entity, bean);
        bean.setMedicineName(entity.getMedicine().getName());
        bean.setPharmacyName(entity.getPharmacy().getName());


        return bean;
    }

    @Override
    public Reservation bean2Entity(ReservationDTO bean) {
        Reservation entity = new Reservation();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }
}
