package com.example.simplepos.controller;

import com.example.simplepos.dto.ProductDTOGet;
import com.example.simplepos.dto.ProductDTOPost;
import com.example.simplepos.service.OrderItemService;
import com.example.simplepos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api-product")
public class ProductController {


    private final ProductService productService;
    private final OrderItemService orderItemService;

    public ProductController(ProductService productService, OrderItemService orderItemService) {
        this.productService = productService;
        this.orderItemService = orderItemService;
    }


    @GetMapping("/all-products")
    public ResponseEntity<List<ProductDTOGet>> getAllProduct(){

        List<ProductDTOGet> productDTOGet = productService.getAllProducts();

        return new ResponseEntity<>(productDTOGet,HttpStatus.OK);
    }

    @GetMapping("/best-selling-product")public ResponseEntity<List<ProductDTOGet>> getBestSellingProduct() {

        return new ResponseEntity<>(orderItemService.getBestSellingProductSKU(), HttpStatus.OK);}



    @PutMapping("/update-product")
    public ResponseEntity<HttpStatus> updateProduct(@RequestBody ProductDTOPost productDTOPost) throws ParseException {

        boolean b = productService.updateProduct(productDTOPost);
        if(b)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/{sku}")
    public ResponseEntity<ProductDTOGet> getEntryById(@PathVariable Long sku){
        return new ResponseEntity<>(productService.getEntryById(sku),HttpStatus.FOUND);
    }


    @DeleteMapping("/delete-product")
    public ResponseEntity<HttpStatus> deleteProduct(@RequestBody ProductDTOPost productDTOPost){
        if(productService.deleteProduct(productDTOPost))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
