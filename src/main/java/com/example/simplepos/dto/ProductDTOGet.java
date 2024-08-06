package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOGet {

    private Long productSKU;
    private String productName;
    private Double productCostPrice;
    private Double productSellingPrice;
    private Double productDiscountPrice;
    private String productDescription;
    private String storageType;
    private Boolean isExpirable;
    private String discountName;
    private String productCategoryName;
    private String productImage;

}