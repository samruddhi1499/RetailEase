package com.example.simplepos.controller;

import com.example.simplepos.dto.ProductDTOGet;
import com.example.simplepos.dto.ProductDTOPost;
import com.example.simplepos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<?> getAllProduct(){

        List<ProductDTOGet> productDTOGet = productService.getAllProducts();

        return new ResponseEntity<>(productDTOGet,HttpStatus.OK);
    }

    @PutMapping("/update-product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTOPost productDTOPost) throws IOException, ParseException {

        Date expiryDate = null;
        if(productDTOPost.getExpiryDate() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            expiryDate = sdf.parse(productDTOPost.getExpiryDate());
        }
        boolean b = productService.updateProduct(productDTOPost, expiryDate);
        if(b)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDTOPost productDTOPost){
        if(productService.deleteProduct(productDTOPost))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
