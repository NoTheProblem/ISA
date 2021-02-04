package ftn.isa.pharmacy.mapper;


import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.dto.MedicineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicineMapper {

    public MedicineDto entityToBean(Medicine medicine) {
        return new MedicineDto(medicine);
    }

    public List<MedicineDto> entitiesToBeans(List<Medicine> medicine) {
        return medicine
                .stream()
                .map(this::entityToBean)
                .collect(Collectors.toList());
    }

}
