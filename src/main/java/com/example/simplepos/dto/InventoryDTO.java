package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

    private Long productSKU;
    private Integer warehouseID;
    private int quantity;
    private ProductDTO product;
    private WarehouseDTO warehouse;
}
