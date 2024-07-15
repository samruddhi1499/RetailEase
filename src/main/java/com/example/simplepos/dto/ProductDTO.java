package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productSKU;
    private String productName;
    private Double productCostPrice;
    private Double productSellingPrice;
    private String productDescription;
    private String storageType;
    private Boolean isExpirable;
    private String expiryDate; // Change to String
    private Integer discountID;
    private String productCategoryName;
    private Integer productQuantity;
    private String warehouseName;
    private String productImage;

}
