package com.example.simplepos.mapper;

import com.example.simplepos.dto.*;
import com.example.simplepos.entity.*;

public class DTOMapper {



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


}


