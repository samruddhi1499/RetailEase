package com.example.simplepos.controller;

import com.example.simplepos.dto.InventoryDTO;
import com.example.simplepos.dto.ProductDTO;
import com.example.simplepos.entity.Inventory;
import com.example.simplepos.service.InventoryService;
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
@RequestMapping("/api-inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    private final ProductService productService;

    public InventoryController(InventoryService inventoryService, ProductService productService) {
        this.inventoryService = inventoryService;
        this.productService = productService;
    }

//    @PostMapping
//    public ResponseEntity<?> addInventory(@RequestBody InventoryDTO inventoryDTO) {
//        inventoryService.addToInventory(inventoryDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//
//    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) throws IOException, ParseException {
        Date expiryDate = null;
        if(productDTO.getIsExpirable()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             expiryDate= sdf.parse(productDTO.getExpiryDate());
        }
        if(productService.addProduct(productDTO, expiryDate))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/update-inventory")
    public ResponseEntity<?> updateInventory(@RequestBody InventoryDTO inventoryDTO) {
        if(inventoryService.updateToInventory(inventoryDTO))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/all-inventory")
    public List<InventoryDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/product/{SKU}")
    public ResponseEntity<?> getEntryById(@PathVariable Long SKU){

        return new ResponseEntity<>(inventoryService.getEntryById(SKU),HttpStatus.OK);
    }

    @GetMapping("/warehouse/{warehouseID}")
    public ResponseEntity<?> getEntryByWarehouseId(@PathVariable Long warehouseID){

        return new ResponseEntity<>(inventoryService.getEntryByWarehouseId(warehouseID),HttpStatus.OK);
    }

    @GetMapping("/product/{SKU}/warehouse/{warehouseID}")
    public ResponseEntity<?> getEntryByPK(@PathVariable Integer warehouseID, @PathVariable Long SKU){

        return new ResponseEntity<>(inventoryService.getEntryByPK(warehouseID,SKU),HttpStatus.OK);
    }

    @GetMapping("/product-category/{name}")
    public ResponseEntity<?> getEntryByProductCategory(@PathVariable String name){

        return new ResponseEntity<>(inventoryService.getEntryByProductCategory(name),HttpStatus.OK);
    }

    @GetMapping("/product-name/{name}")
    public ResponseEntity<?> getEntryByProductName(@PathVariable String name){

        return new ResponseEntity<>(inventoryService.getEntryByProductName(name),HttpStatus.OK);
    }
    @GetMapping("/lower/{lowerEnd}/higher/{higherEnd}")
    public ResponseEntity<?> getEntryByPrice(@PathVariable Double lowerEnd, @PathVariable Double higherEnd){

        return new ResponseEntity<>(inventoryService.getEntryByPrice(lowerEnd,higherEnd),HttpStatus.OK);
    }



    @DeleteMapping
    public ResponseEntity<?> deleteInventory(@RequestBody InventoryDTO inventoryDTO) {
        inventoryService.deleteFromInventory(inventoryDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
