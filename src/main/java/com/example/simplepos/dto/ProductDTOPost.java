package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOPost {

    private Long productSKU;
    private String productName;
    private Double productCostPrice;
    private Double productSellingPrice;
    private String productDescription;
    private String storageType;
    private Boolean isExpirable;
    private String expiryDate;
    private String discountName;
    private String productCategoryName;
    private Integer productQuantity;
    private String warehouseName;
    private String productImage;

}
