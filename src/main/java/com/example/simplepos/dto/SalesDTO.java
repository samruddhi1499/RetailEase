package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesDTO {

    private Long salesId;

    private Long transactionId;
    private String transactionDate;

    private String decision;
}
