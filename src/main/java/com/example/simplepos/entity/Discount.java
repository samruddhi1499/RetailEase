package com.example.simplepos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer discountId;
    private String discountName;
    private Double discountPercent;
    private Boolean isActive;
    private Date discountExpiryDate;
    @OneToMany(mappedBy = "discount")
    @JsonIgnore
    private List<Product> products;
}
