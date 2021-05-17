package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.PurchaseOrder;
import ftn.isa.pharmacy.repository.PurchaseOrderRepository;
import ftn.isa.pharmacy.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public OrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }
}
