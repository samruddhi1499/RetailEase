package com.example.simplepos.controller;

import com.example.simplepos.dto.ProductDTO;
import com.example.simplepos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){

        productService.addProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct(){

        List<ProductDTO> productDTO = productService.getAllProducts();

        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO){

        productService.updateProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDTO productDTO){

        productService.deleteProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
