package ftn.isa.pharmacy.mapper;

import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.model.Pharmacy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PharmacyMapper {

    public PharmacyDto entityToBean(Pharmacy pharmacy) {
        return new PharmacyDto(pharmacy);
    }

    public List<PharmacyDto> entitiesToBeans(List<Pharmacy> pharmacies) {
        return pharmacies
                .stream()
                .map(this::entityToBean)
                .collect(Collectors.toList());
    }

}
