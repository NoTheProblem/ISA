package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.PurchaseOrderDTO;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.PurchaseOrder;

import java.util.Collection;
import java.util.List;

public interface OrderService {

    List<PurchaseOrder> getAll();

    void addPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    Collection<PurchaseOrderDTO> getAllActive();
}
