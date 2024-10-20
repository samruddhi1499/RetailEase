package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long orderId;
    private Long productSku;
    private String productName;
    private Double productCostPrice;
    private Double productSellingPrice;
    private Integer orderQuantity;

}
