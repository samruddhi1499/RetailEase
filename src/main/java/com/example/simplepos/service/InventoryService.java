package com.example.simplepos.service;

import com.example.simplepos.dto.InventoryDTO;
import com.example.simplepos.entity.Inventory;
import com.example.simplepos.entity.InventoryPKId;
import com.example.simplepos.entity.Product;
import com.example.simplepos.entity.Warehouse;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.InventoryRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {


    private final InventoryRepository inventoryRepository;

    private final ProductService productService;

    private final WarehouseService warehouseService;

    public InventoryService(InventoryRepository inventoryRepository, @Lazy ProductService productService, WarehouseService warehouseService) {
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
        this.warehouseService = warehouseService;
    }

    public boolean addToInventory(InventoryDTO inventoryDTO) throws ParseException {
        // Retrieve Product and Warehouse entities from their respective services
        Product product = productService.getProductById(inventoryDTO.getProductSKU());
        Warehouse warehouse = warehouseService.getWarehouseById(inventoryDTO.getWarehouseID());

        // Create Inventory entity
        Inventory inventory = new Inventory();
        InventoryPKId id = new InventoryPKId();
        id.setProductSKU(inventoryDTO.getProductSKU());
        id.setWarehouseID(inventoryDTO.getWarehouseID());
        inventory.setId(id);
        inventory.setQuantity(inventoryDTO.getQuantity());
        inventory.setProduct(product);
        inventory.setWarehouse(warehouse);
        if (product.getIsExpirable()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            id.setExpiryDate(sdf.parse(inventoryDTO.getExpiryDate()));
        } else {

            id.setExpiryDate(new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31"));
            Inventory byId = inventoryRepository.findById(id).orElse(null);
            if (byId != null)
                return false;
        }

        // Save Inventory entity to database
        inventoryRepository.save(inventory);
        return true;

    }

    public boolean updateToInventory(InventoryDTO inventoryDTO) throws ParseException {

        Date expiryDate;
        if(inventoryDTO.getExpiryDate().isEmpty()){
            expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
        }
        else{
            expiryDate = convertToDate(inventoryDTO.getExpiryDate());
        }

        Inventory byId = inventoryRepository.findById(new InventoryPKId(inventoryDTO.getProductSKU(), inventoryDTO.getWarehouseID(),expiryDate)).orElse(null);
        if(byId != null) {
            byId.setQuantity(inventoryDTO.getQuantity());
            // Save Inventory entity to database
            inventoryRepository.save(byId);
            inventoryRepository.deleteIfQuantityZero();
            return true;
        }
        return false;

    }

    public void updateInventoryForTransaction(Integer quantity, Long sku) throws ParseException {

        List<Inventory> inventories = inventoryRepository.findBySKU(sku);
        Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31");
        if(inventories.get(0).getId().getExpiryDate() != expiryDate){

            int count = 0;
            Date specificDate = inventories.get(0).getId().getExpiryDate();
            for(Inventory inventory : inventories){

                if (specificDate.after(inventory.getId().getExpiryDate())) {
                    count ++;
                    specificDate = inventory.getId().getExpiryDate();

                }
            }
            inventories.get(count).setQuantity(inventories.get(count).getQuantity() - quantity);
            inventoryRepository.save(inventories.get(count));

        }
        else{

            inventories.get(0).setQuantity(inventories.get(0).getQuantity() - quantity);
            inventoryRepository.save(inventories.get(0));

        }





    }

    public void deleteFromInventory(InventoryDTO inventoryDTO) throws ParseException {

        Date expiryDate = convertToDate(inventoryDTO.getExpiryDate());
        Inventory byId = inventoryRepository.findById(new InventoryPKId(inventoryDTO.getProductSKU(), inventoryDTO.getWarehouseID(),expiryDate)).orElse(null);
        if(byId != null) {
            inventoryRepository.deleteById(new InventoryPKId(inventoryDTO.getProductSKU(), inventoryDTO.getWarehouseID(),expiryDate));
        }

    }



    public List<InventoryDTO> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findByAllNotZero();
        return inventories.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<InventoryDTO> getEntryById(Long sku) {

        List<Inventory> inventories = inventoryRepository.findBySKU(sku);
        return inventories.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<InventoryDTO> getEntryByWarehouseId(Long warehouseID) {

        List<Inventory> inventories = inventoryRepository.findByWarehouseID(warehouseID);
        return inventories.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InventoryDTO getEntryByPK(Integer warehouseID, Long sku, Date expiryDate) {
        Inventory byId = inventoryRepository.findById(new InventoryPKId(sku, warehouseID, expiryDate)).orElse(null);

        if(byId == null)
            return null;
        return DTOMapper.toDTO(byId);

    }


    public List<InventoryDTO> getEntryByProductCategory(String name) {
        List<Inventory> byProductCategory = inventoryRepository.findByProductCategory(name);
        if(byProductCategory.isEmpty())
            return Collections.emptyList();
        return byProductCategory.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<InventoryDTO> getEntryByProductName(String name) {
        List<Inventory> byProductCategory = inventoryRepository.findByProductName(name);
        if(byProductCategory.isEmpty())
            return Collections.emptyList();
        return byProductCategory.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<InventoryDTO> getEntryByPrice(Double lowerEnd, Double higherEnd) {
        List<Inventory> byProductCategory = inventoryRepository.findBySellingPrice(lowerEnd,higherEnd);
        if(byProductCategory.isEmpty())
            return Collections.emptyList();
        return byProductCategory.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }



    private Date convertToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);

    }
}