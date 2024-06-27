package com.example.simplepos.mapper;

import com.example.simplepos.dto.InventoryDTO;
import com.example.simplepos.dto.ProductDTO;
import com.example.simplepos.dto.WarehouseDTO;
import com.example.simplepos.entity.*;
import com.example.simplepos.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.stream.Collectors;

public class DTOMapper {

    @Autowired
    private static ProductCategoryService productCategoryService;

    public static InventoryDTO toDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setProductSKU(inventory.getId().getProductSKU());
        dto.setWarehouseID(inventory.getId().getWarehouseID());
        dto.setQuantity(inventory.getQuantity());
        dto.setProduct(toDTO(inventory.getProduct()));
        dto.setWarehouse(toDTO(inventory.getWarehouse()));
        return dto;
    }

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductSKU(product.getSKU());
        dto.setProductName(product.getProductName());
        dto.setProductCostPrice(product.getProductCostPrice());
        dto.setProductSellingPrice(product.getProductSellingPrice());
        dto.setProductDescription(product.getProductDescription());
        dto.setStorageType(product.getStorageType());
        dto.setIsExpirable(product.getIsExpirable());
        dto.setExpiryDate(new SimpleDateFormat("yyyy-MM-dd").format(product.getExpiryDate()));
        dto.setDiscountID(product.getDiscountID());
        String encodedImage = (product.getProductImage() != null) ? encodeImageToBase64(product.getProductImage()) : null;
        dto.setProductImage(encodedImage);

        if (product.getProductCategory() != null) {
            dto.setProductCategoryID(String.valueOf(product.getProductCategory().getProductCategoryID()));
        }
        return dto;
    }

    private static String encodeImageToBase64(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }



    public static WarehouseDTO toDTO(Warehouse warehouse) {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setWarehouseID(warehouse.getWarehouseID());
        dto.setWarehouseName(warehouse.getWarehouseName());
        return dto;
    }
//
//    public static Inventory toEntity(InventoryDTO dto) {
//        Inventory inventory = new Inventory();
//        InventoryPKId id = new InventoryPKId();
//        id.setProductSKU(dto.getProductSKU());
//        id.setWarehouseID(dto.getWarehouseID());
//        inventory.setId(id);
//        inventory.setQuantity(dto.getQuantity());
//        inventory.setProduct(toEntity(dto.getProduct()));
//        inventory.setWarehouse(toEntity(dto.getWarehouse()));
//        return inventory;
//    }
//
//    public static Product toEntity(ProductDTO dto) {
//        Product product = new Product();
//        Inventory inventory = new Inventory();
//        product.setSKU(dto.getProductSKU());
//        product.setProductName(dto.getProductName());
//        product.setProductCostPrice(dto.getProductCostPrice());
//        product.setProductSellingPrice(dto.getProductSellingPrice());
//        product.setProductDescription(dto.getProductDescription());
//        product.setStorageType(dto.getStorageType());
//        product.setIsExpirable(dto.getIsExpirable());
//        product.setExpiryDate(dto.getExpiryDate());
//        product.setDiscountID(dto.getDiscountID());
//        // Assuming you have a method to fetch a ProductCategory by ID
//        if (dto.getProductCategoryID() != null) {
//            product.setProductCategory(fetchProductCategoryById(dto.getProductCategoryID()));
//        }
//
//        return product;
//    }
//
//    public static Warehouse toEntity(WarehouseDTO dto) {
//        Warehouse warehouse = new Warehouse();
//        warehouse.setWarehouseID(dto.getWarehouseID());
//        warehouse.setWarehouseName(dto.getWarehouseName());
//        return warehouse;
//    }

    // Stub for fetching a ProductCategory by ID - implement as needed
    private static ProductCategory fetchProductCategoryById(Integer id) {

        return productCategoryService.getProductCategoryById(id);
    }
}


