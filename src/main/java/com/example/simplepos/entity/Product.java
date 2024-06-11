package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    private String SKU;
    private String productName;
    private Double productCostPrice;
    private Double productSellingPrice;
    private String productDescription;
    private String storageType;
    private Boolean isExpirable;
    private Date expiryDate;
    private Long discountID;

    @ManyToOne
    @JoinColumn(name = "productCategoryID")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product")
    private List<Inventory> inventory;


}
