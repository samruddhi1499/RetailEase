package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long transactionId;

    public TransactionDTO(Double amountAfterTax, Long employeeId, Long orderId) {
        this.amountAfterTax = amountAfterTax;
        this.employeeId = employeeId;
        this.orderId = orderId;
    }

    private Double amountAfterTax;
    private String transactionDateAndTime;
    private Long employeeId;
    private Long orderId;
    private OrderDTO orderDTO;
}
