package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
@ToString(exclude = {"inventory"})
public class Product {

    @Id
    @Column(name = "SKU")
    private Long SKU;
    private String productName;
    private Double productCostPrice;
    private Double productSellingPrice;
    private String productDescription;
    private String storageType;
    private Boolean isExpirable;
    private Date expiryDate;
    private Integer discountID;

    @ManyToOne
    @JoinColumn(name = "productCategoryID")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product")
    private List<Inventory> inventory;

    @Lob
    @Column(name = "productImage", columnDefinition = "BLOB")
    private byte[] productImage;  // Image data


}
