package com.example.simplepos.service;

import com.example.simplepos.entity.ProductCategory;
import com.example.simplepos.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {


    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory getProductCategoryById(Integer id){
        return productCategoryRepository.findById(id).orElse(null);
    }
}
