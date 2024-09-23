package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long transactionId;
    private Double amountAfterTax;
    private String transactionDateAndTime;
    private Long employeeId;
    private Long orderId;
    private OrderDTO orderDTO;
}
