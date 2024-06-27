package com.example.simplepos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product_Category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productCategoryID")
    private Integer productCategoryID;
    private String categoryName;

    @OneToMany(mappedBy = "productCategory")
    @JsonIgnore
    private List<Product> products;

}
