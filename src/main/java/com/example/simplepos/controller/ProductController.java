package com.example.simplepos.controller;

import com.example.simplepos.dto.InventoryDTO;
import com.example.simplepos.dto.ProductDTOGet;
import com.example.simplepos.dto.ProductDTOPost;
import com.example.simplepos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api-product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all-products")
    public ResponseEntity<List<ProductDTOGet>> getAllProduct(){

        List<ProductDTOGet> productDTOGet = productService.getAllProducts();

        return new ResponseEntity<>(productDTOGet,HttpStatus.OK);
    }

    @PutMapping("/update-product")
    public ResponseEntity<HttpStatus> updateProduct(@RequestBody ProductDTOPost productDTOPost) throws ParseException {

        boolean b = productService.updateProduct(productDTOPost);
        if(b)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/{sku}")
    public ResponseEntity<ProductDTOGet> getEntryById(@PathVariable Long sku){
        return new ResponseEntity<>(productService.getEntryById(sku),HttpStatus.OK);
    }


    @DeleteMapping("/delete-product")
    public ResponseEntity<HttpStatus> deleteProduct(@RequestBody ProductDTOPost productDTOPost){
        if(productService.deleteProduct(productDTOPost))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
