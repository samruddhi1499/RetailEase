package com.example.simplepos.controller;

import com.example.simplepos.dto.ProductDTO;
import com.example.simplepos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api-product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@ModelAttribute ProductDTO productDTO, @RequestParam("image") MultipartFile image) throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date expiryDate = sdf.parse(productDTO.getExpiryDate());
        productService.addProduct(productDTO,expiryDate,image);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct(){

        List<ProductDTO> productDTO = productService.getAllProducts();

        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@ModelAttribute ProductDTO productDTO, @RequestParam("image") MultipartFile image) throws IOException, ParseException{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date expiryDate = sdf.parse(productDTO.getExpiryDate());
        productService.updateProduct(productDTO,expiryDate,image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDTO productDTO){

        productService.deleteProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
