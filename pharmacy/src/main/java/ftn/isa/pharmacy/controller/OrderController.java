package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.PurchaseOrderDTO;
import ftn.isa.pharmacy.mapper.impl.PurchaseOrderMapperImpl;
import ftn.isa.pharmacy.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final PurchaseOrderMapperImpl orderMapper;

    public OrderController(OrderService orderService, PurchaseOrderMapperImpl orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PurchaseOrderDTO>> getAll() {
        Collection<PurchaseOrderDTO> purchaseOrderDTOS = orderMapper.entity2Bean(orderService.getAll());
        return ResponseEntity.ok(purchaseOrderDTOS);
    }

    @PostMapping("/addPurchaseOrder")
    public void addPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        orderService.addPurchaseOrder(purchaseOrderDTO);
        System.out.println(purchaseOrderDTO);

    }

    @GetMapping(value = "/getAllActive")
    public ResponseEntity<Collection<PurchaseOrderDTO>> getAllActive() {
        Collection<PurchaseOrderDTO> purchaseOrderDTOS = orderService.getAllActive();
        return ResponseEntity.ok(purchaseOrderDTOS);
    }

}
