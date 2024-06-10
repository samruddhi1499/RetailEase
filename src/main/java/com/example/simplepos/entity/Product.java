package com.example.simplepos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    private int productID;
    private String productName;
    private double productCostPrice;

    private double productSellingPrice;
    private String SKU;

    private  String productDescription;

    private String productCategoryID;

//    private storeageType

    private boolean isExpirable;

    private Date expiraryDate;

    private int inventroyID;

    private int discountID;

}
