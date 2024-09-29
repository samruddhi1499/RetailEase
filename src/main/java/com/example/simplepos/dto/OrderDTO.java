package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private Double totalAmount;
    private String orderDateAndTime;
    private Double amountAfterTax;
    private Long employeeId;
    private List<OrderItemDTO> orderItems;
}

