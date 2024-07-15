package com.example.simplepos.controller;

import com.example.simplepos.dto.ProductDTO;
import com.example.simplepos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


//    @GetMapping
//    public ResponseEntity<?> getAllProduct(){
//
//        List<ProductDTO> productDTO = productService.getAllProducts();
//
//        return new ResponseEntity<>(productDTO,HttpStatus.OK);
//    }


    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDTO productDTO){
        productService.deleteProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
