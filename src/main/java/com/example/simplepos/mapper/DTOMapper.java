package com.example.simplepos.mapper;

import com.example.simplepos.dto.*;
import com.example.simplepos.entity.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

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
            dto.setAboutToExpire(ChronoUnit.DAYS.between(currentDate, expiryDate));

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


    public static TransactionDTO toDTO(Transaction transaction) {

        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId(transaction.getTransactionId());
        dto.setOrderId(transaction.getOrder().getOrderId());
        dto.setAmountAfterTax(transaction.getAmountAfterTax());
        dto.setTransactionDateAndTime(String.valueOf(transaction.getTransactionDateAndTime()));
        dto.setOrderDTO(toDTO(transaction.getOrder()));
        return dto;
    }

    public static OrderDTO toDTO(Order order) {

        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderDateAndTime(String.valueOf(order.getOrderDateAndTime()));

        List<OrderItemDTO> collect = order.getOrderItems().stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
        dto.setOrderItems(collect);

        return dto;


    }

    public static OrderItemDTO toDTO(OrderItem orderItem) {

        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderId(orderItem.getId().getOrderId());
        dto.setOrderQuantity(orderItem.getOrderQuantity());
        dto.setProductSku(orderItem.getId().getSKU());
        return dto;

    }


}


