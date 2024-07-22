package com.example.simplepos.service;

import com.example.simplepos.dto.ProductCategoryDTO;
import com.example.simplepos.entity.Product;
import com.example.simplepos.entity.ProductCategory;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {


    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory getProductCategoryByName(String productCategoryName){
        return productCategoryRepository.findByCategoryName(productCategoryName);
    }

    public List<ProductCategoryDTO> getAllProductsCategories() {

        List<ProductCategory> allProductCategories = productCategoryRepository.findAll();
        return allProductCategories.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());


    }

    public void addCategory(ProductCategoryDTO categoryDTO) {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName(categoryDTO.getProductCategoryName());
        productCategoryRepository.save(productCategory);

    }

    public boolean updateCategory(ProductCategoryDTO categoryDTO) {

        ProductCategory productCategory = productCategoryRepository.findById(categoryDTO.getProductCategoryId()).orElse(null);
        if(productCategory != null){
            productCategory.setCategoryName(categoryDTO.getProductCategoryName());
            productCategoryRepository.save(productCategory);
            return true;
        }
        return false;

    }
}
