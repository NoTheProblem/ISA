package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.BidDTO;
import ftn.isa.pharmacy.dto.PurchaseOrderDTO;
import ftn.isa.pharmacy.mapper.impl.*;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.OrderService;
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
    private final BidRepository bidRepository;
    private final BidMapperImpl bidMapper;
    private final SupplierMapperImpl supplierMapper;
    private final MailServiceImpl mailService;
    private final SupplierRepository supplierRepository;
    private final MedicineQuantityOrderRepository medicineQuantityOrderRepository;
    private final MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository;

    public OrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderMapperImpl purchaseOrderMapper, PharmacyAdminRepository pharmacyAdminRepository, MedicineMapperImpl medicineMapper, QuantityMapperImpl quantityMapper, BidRepository bidRepository, BidMapperImpl bidMapper, SupplierMapperImpl supplierMapper, MailServiceImpl mailService, SupplierRepository supplierRepository, MedicineQuantityOrderRepository medicineQuantityOrderRepository, MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderMapper = purchaseOrderMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.quantityMapper = quantityMapper;
        this.bidRepository = bidRepository;
        this.bidMapper = bidMapper;
        this.supplierMapper = supplierMapper;
        this.mailService = mailService;
        this.supplierRepository = supplierRepository;
        this.medicineQuantityOrderRepository = medicineQuantityOrderRepository;
        this.medicineQuantityPharmacyRepository = medicineQuantityPharmacyRepository;
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

    @Override
    public PurchaseOrderDTO getOrderByID(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(id);
        return purchaseOrderMapper.entity2Bean(purchaseOrder);
    }

    @Override
    public Collection<BidDTO> getBidsForOrder(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(id);
        Collection<Bid> bids = bidRepository.findAllByPurchaseOrder(purchaseOrder);
        Collection<BidDTO> bidDTOS = new HashSet<>();
        for (Bid b:bids) {
            Supplier s = b.getSupplier();
            BidDTO bidDTO = bidMapper.entity2Bean(b);
            bidDTO.setSupplier(supplierMapper.entity2Bean(s));
            bidDTOS.add(bidDTO);
        }
        return bidDTOS;
    }

    @Override
    public void confirmBid(BidDTO bidDTO, Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(id);
        if(purchaseOrder.getStatus().equals("obradjen")){
            return;
        }
        if(purchaseOrder.getPharmacyAdmin().getId()!= getPharmacyAdmin().getId()){
            return;
            // TODO exception
        }
        // TODO check date again
        Bid bid = bidMapper.bean2Entity(bidDTO);
        Long suppID = bidDTO.getSupplier().getId();
        Supplier supplier = supplierRepository.getOne(suppID);
        purchaseOrder.setStatus("obradjen");
        purchaseOrder.setPrice(bid.getPrice());
        purchaseOrder.setChosenSupplier(supplier);
        Set<PurchaseOrder> wonOrders = supplier.getWonPurchaseOrders();
        wonOrders.add(purchaseOrder);
        supplier.setWonPurchaseOrders(wonOrders);
        // Set<Bid> allBids = purchaseOrder.getBids();
        List<Bid> allBids = bidRepository.findAllByPurchaseOrder(purchaseOrder);
        String subj = "Status porudzbenice";
        String text = "Obavestavamo Vas da Vasa ponuda za porudzbenicu broj #" + purchaseOrder.getId() + " odbijena ";
        System.out.println(allBids.size());
        for (Bid b:allBids) {
            if(b.getId() == bid.getId()){
                b.setStatus("odobrena");
                mailService.purchaseOrderNotification(b.getSupplier().getEmail(),subj, "Obavestavamo Vas da Vasa ponuda za porudzbenicu broj #" + purchaseOrder.getId() + " prihvacena ");
                bidRepository.save(b);
            }
            else {
                b.setStatus("odbijena");
                mailService.purchaseOrderNotification(b.getSupplier().getEmail(), subj, text);
                bidRepository.save(b);
            }
        }
        Long phaID = purchaseOrder.getPharmacyAdmin().getPharmacy().getId();
        purchaseOrderRepository.save(purchaseOrder);
        supplierRepository.save(supplier);
        List<MedicineQuantityOrder> medicineQuantityOrders =  medicineQuantityOrderRepository.findAllByPurchaseOrder(purchaseOrder);
        for (MedicineQuantityOrder mQ: medicineQuantityOrders) {
            medicineQuantityPharmacyRepository.updateQuan(phaID, mQ.getMedicine(), mQ.getQuantity());
        }
    }

    @Override
    public void deleteOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.bean2Entity(purchaseOrderDTO);
        if(bidRepository.findAllByPurchaseOrder(purchaseOrder).size() != 0){
            return;
            //TODO exception
        }
        purchaseOrderRepository.deleteById(purchaseOrder.getId());
    }

    @Override
    public void updateOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.bean2Entity(purchaseOrderDTO);
        if(bidRepository.findAllByPurchaseOrder(purchaseOrder).size() != 0){
            return;
            //TODO exception
        }
        purchaseOrderRepository.save(purchaseOrder);

    }


    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            return pharmacyAdmin;
        }
        return null;
    }
}
