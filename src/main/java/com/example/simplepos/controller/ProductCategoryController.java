package com.example.simplepos.controller;

import com.example.simplepos.dto.ProductCategoryDTO;
import com.example.simplepos.entity.ProductCategory;
import com.example.simplepos.service.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-product-category")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;


    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories(){

        List<ProductCategoryDTO> productCategories = productCategoryService.getAllProductsCategories();

        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody ProductCategoryDTO categoryDTO){

        productCategoryService.addCategory(categoryDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody ProductCategoryDTO categoryDTO){

        if(productCategoryService.updateCategory(categoryDTO))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteCategory(@RequestParam Integer id){
//
//        productCategoryService.deleteCategory(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//
//    }
}
