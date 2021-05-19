package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.PurchaseOrderDTO;
import ftn.isa.pharmacy.mapper.impl.PurchaseOrderMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.PurchaseOrderRepository;
import ftn.isa.pharmacy.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapperImpl purchaseOrderMapper;
    private final PharmacyAdminRepository pharmacyAdminRepository;

    public OrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderMapperImpl purchaseOrderMapper, PharmacyAdminRepository pharmacyAdminRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderMapper = purchaseOrderMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
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
            purchaseOrder.setPharmacyAdmin(pharmacyAdminOptional.get());
            purchaseOrderRepository.save(purchaseOrder);
        }

    }
}
