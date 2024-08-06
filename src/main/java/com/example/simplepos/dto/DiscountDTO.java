package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDTO {

    private Integer discountId;
    private String discountName;
    private Double discountPercent;
    private Boolean isActive;
    private Date discountExpiryDate;
}


