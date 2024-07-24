package com.example.simplepos.controller;

import com.example.simplepos.dto.ProductDTO;
import com.example.simplepos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @PutMapping("/update-product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) throws IOException, ParseException {

        Date expiryDate = null;
        if(productDTO.getExpiryDate() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            expiryDate = sdf.parse(productDTO.getExpiryDate());
        }
        boolean b = productService.updateProduct(productDTO, expiryDate);
        if(b)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDTO productDTO){
        if(productService.deleteProduct(productDTO))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
