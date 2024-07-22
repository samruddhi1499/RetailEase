package com.example.simplepos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDTO {

    private Integer productCategoryId;

    private String productCategoryName;
}
