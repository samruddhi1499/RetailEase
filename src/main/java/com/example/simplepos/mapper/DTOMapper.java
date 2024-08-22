package com.example.simplepos.mapper;

import com.example.simplepos.dto.*;
import com.example.simplepos.entity.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DTOMapper {



    public static InventoryDTO toDTO(Inventory inventory)  {
        InventoryDTO dto = new InventoryDTO();
        dto.setProductSKU(inventory.getId().getProductSKU());
        dto.setWarehouseID(inventory.getId().getWarehouseID());
        dto.setQuantity(inventory.getQuantity());
        if (inventory.getId().getExpiryDate() != null) {
            dto.setExpiryDate(String.valueOf(inventory.getId().getExpiryDate()));
            LocalDate expiryDate = inventory.getId().getExpiryDate().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate currentDate = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(currentDate, expiryDate);
            if(daysBetween < 20 && daysBetween > 0)
                dto.setAboutToExpire(daysBetween +" to expire!!");
            else if(daysBetween < 0)
                dto.setAboutToExpire("Product is expired");
            else
                dto.setAboutToExpire("Not Expired");

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
        dto.setProductImage(product.getProductImage());

        if(product.getDiscount() != null && product.getDiscount().getIsActive()){
            dto.setDiscountName(product.getDiscount().getDiscountName());
        }
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


        dto.setProductImage(product.getProductImage());

        if(product.getDiscount() != null)
            if(Boolean.TRUE.equals(product.getDiscount().getIsActive())){
                dto.setDiscountName(product.getDiscount().getDiscountName());
                dto.setProductDiscountPrice(product.getProductDiscountPrice());
            }



        if (product.getProductCategory() != null) {
            dto.setProductCategoryName(String.valueOf(product.getProductCategory().getCategoryName()));
            dto.setProductCategoryId(product.getProductCategory().getProductCategoryID());
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

    public static DiscountDTO toDTO(Discount discount) {
        DiscountDTO dto = new DiscountDTO();
        dto.setDiscountId(discount.getDiscountId());
        dto.setDiscountName(discount.getDiscountName());
        dto.setDiscountPercent(discount.getDiscountPercent());
        dto.setDiscountExpiryDate(discount.getDiscountExpiryDate());
        dto.setIsActive(discount.getIsActive());
        return dto;
    }




}


