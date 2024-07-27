package com.example.simplepos.mapper;

import com.example.simplepos.dto.*;
import com.example.simplepos.entity.*;
import com.example.simplepos.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class DTOMapper {

    @Autowired
    private static ProductCategoryService productCategoryService;

    public static InventoryDTO toDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setProductSKU(inventory.getId().getProductSKU());
        dto.setWarehouseID(inventory.getId().getWarehouseID());
        dto.setQuantity(inventory.getQuantity());
        if (inventory.getId().getExpiryDate() != null) {
            dto.setExpiryDate(String.valueOf(inventory.getId().getExpiryDate()));
        } else {
            dto.setExpiryDate(null);
        }
        dto.setProduct(toDTOGet(inventory.getProduct()));
        dto.setWarehouse(toDTO(inventory.getWarehouse()));
        return dto;
    }

    public static ProductDTOPost toDTO(Product product) {
        ProductDTOPost dto = new ProductDTOPost();
        dto.setProductSKU(product.getSKU());
        dto.setProductName(product.getProductName());
        dto.setProductCostPrice(product.getProductCostPrice());
        dto.setProductSellingPrice(product.getProductSellingPrice());
        dto.setProductDescription(product.getProductDescription());
        dto.setStorageType(product.getStorageType());
        dto.setIsExpirable(product.getIsExpirable());

        dto.setDiscountID(product.getDiscountID());
       // String encodedImage = (product.getProductImage() != null) ? encodeImageToBase64(product.getProductImage()) : null;
        dto.setProductImage(product.getProductImage());


        if (product.getProductCategory() != null) {
            dto.setProductCategoryName(String.valueOf(product.getProductCategory().getCategoryName()));
        }
        return dto;
    }

    public static ProductDTOGet toDTOGet(Product product) {
        ProductDTOGet dto = new ProductDTOGet();
        dto.setProductSKU(product.getSKU());
        dto.setProductName(product.getProductName());
        dto.setProductCostPrice(product.getProductCostPrice());
        dto.setProductSellingPrice(product.getProductSellingPrice());
        dto.setProductDescription(product.getProductDescription());
        dto.setStorageType(product.getStorageType());
        dto.setIsExpirable(product.getIsExpirable());

        dto.setDiscountID(product.getDiscountID());
        // String encodedImage = (product.getProductImage() != null) ? encodeImageToBase64(product.getProductImage()) : null;
        dto.setProductImage(product.getProductImage());


        if (product.getProductCategory() != null) {
            dto.setProductCategoryName(String.valueOf(product.getProductCategory().getCategoryName()));
        }
        return dto;
    }





    public static WarehouseDTO toDTO(Warehouse warehouse) {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setWarehouseID(warehouse.getWarehouseID());
        dto.setWarehouseName(warehouse.getWarehouseName());
        return dto;
    }

    public static ProductCategoryDTO toDTO(ProductCategory productCategory) {
        ProductCategoryDTO dto = new ProductCategoryDTO();
        dto.setProductCategoryId(productCategory.getProductCategoryID());
        dto.setProductCategoryName(productCategory.getCategoryName());
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
//    private static ProductCategory fetchProductCategoryById(Integer id) {
//
//        return productCategoryService.getProductCategoryById(id);
//    }
}


