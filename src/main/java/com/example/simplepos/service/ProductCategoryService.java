package com.example.simplepos.service;

import com.example.simplepos.dto.ProductCategoryDTO;
import com.example.simplepos.entity.ProductCategory;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {



    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public ProductCategory getProductCategoryByName(String productCategoryName){
        return productCategoryRepository.findByCategoryName(productCategoryName);
    }

    public List<ProductCategoryDTO> getAllProductsCategories() {

        List<ProductCategory> allProductCategories = productCategoryRepository.findAll();
        return allProductCategories.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());


    }

    public boolean addCategory(ProductCategoryDTO categoryDTO) {

        ProductCategory productCategoryCheck = productCategoryRepository.findByCategoryName(categoryDTO.getProductCategoryName());

        if(productCategoryCheck == null){

            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategoryName(categoryDTO.getProductCategoryName());
            productCategoryRepository.save(productCategory);

            return true;
        }
        return false;

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
