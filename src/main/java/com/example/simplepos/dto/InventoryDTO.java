package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

    private Long productSKU;
    private Integer warehouseID;
    private int quantity;
    private String expiryDate;
    private ProductDTO product;
    private WarehouseDTO warehouse;
}
