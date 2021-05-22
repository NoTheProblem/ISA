package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.PurchaseOrderDTO;
import ftn.isa.pharmacy.mapper.impl.MedicineMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PurchaseOrderMapperImpl;
import ftn.isa.pharmacy.mapper.impl.QuantityMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.PurchaseOrderRepository;
import ftn.isa.pharmacy.service.OrderService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapperImpl purchaseOrderMapper;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final QuantityMapperImpl quantityMapper;

    public OrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderMapperImpl purchaseOrderMapper, PharmacyAdminRepository pharmacyAdminRepository, MedicineMapperImpl medicineMapper, QuantityMapperImpl quantityMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderMapper = purchaseOrderMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.quantityMapper = quantityMapper;
    }

    @Override
    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public void addPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PurchaseOrder purchaseOrder = purchaseOrderMapper.bean2Entity(purchaseOrderDTO);
            Set<MedicineQuantityOrder> orderMedicines = quantityMapper.quantityOrderDTOtoQuantityOrder(purchaseOrderDTO.getMedQuan(),purchaseOrder);
            purchaseOrder.setPharmacyAdmin(pharmacyAdminOptional.get());
            purchaseOrder.setStatus("created");
            purchaseOrder.setCreateDate(new Date());
            purchaseOrder.setOrderMedicines(orderMedicines);
            purchaseOrderRepository.save(purchaseOrder);
        }

    }

    @Override
    public Collection<PurchaseOrderDTO> getAllActive() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAllByOrderByEndDateAsc();

        Collection<PurchaseOrderDTO> purchaseOrderDTOS = purchaseOrderMapper.entity2Bean(purchaseOrders);
        return purchaseOrderDTOS;
        }
}
