package com.example.simplepos.service;

import com.example.simplepos.dto.InventoryDTO;
import com.example.simplepos.entity.Inventory;
import com.example.simplepos.entity.InventoryPKId;
import com.example.simplepos.entity.Product;
import com.example.simplepos.entity.Warehouse;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.InventoryRepository;

import java.util.Collections;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    public void addToInventory(InventoryDTO inventoryDTO) {
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

        // Save Inventory entity to database
        inventoryRepository.save(inventory);

    }

    public boolean updateToInventory(InventoryDTO inventoryDTO) {

        Inventory byId = inventoryRepository.findById(new InventoryPKId(inventoryDTO.getProductSKU(), inventoryDTO.getWarehouseID())).orElse(null);
        if(byId != null) {
            byId.setQuantity(inventoryDTO.getQuantity());
            // Save Inventory entity to database
            inventoryRepository.save(byId);
            return true;
        }
        return false;

    }

    public void deleteFromInventory(InventoryDTO inventoryDTO) {
        Inventory byId = inventoryRepository.findById(new InventoryPKId(inventoryDTO.getProductSKU(), inventoryDTO.getWarehouseID())).orElse(null);
        if(byId != null) {
            inventoryRepository.deleteById(new InventoryPKId(inventoryDTO.getProductSKU(), inventoryDTO.getWarehouseID()));
        }

    }



    public List<InventoryDTO> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
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

    public InventoryDTO getEntryByPK(Integer warehouseID, Long sku) {
        Inventory byId = inventoryRepository.findById(new InventoryPKId(sku, warehouseID)).orElse(null);

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
}