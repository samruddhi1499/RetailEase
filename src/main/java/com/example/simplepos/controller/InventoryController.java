package com.example.simplepos.controller;

import com.example.simplepos.dto.InventoryDTO;
import com.example.simplepos.dto.ProductDTOPost;
import com.example.simplepos.service.InventoryService;
import com.example.simplepos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @PostMapping
    public ResponseEntity<HttpStatus> addInventory(@RequestBody InventoryDTO inventoryDTO) throws ParseException {
        inventoryService.addToInventory(inventoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/add-product")
    public ResponseEntity<HttpStatus> addProduct(@RequestBody ProductDTOPost productDTOPost) throws ParseException {
        if(productService.addProduct(productDTOPost))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/update-inventory")
    public ResponseEntity<HttpStatus> updateInventory(@RequestBody InventoryDTO inventoryDTO) throws ParseException {
        if(inventoryService.updateToInventory(inventoryDTO))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/all-inventory")
    public List<InventoryDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/product/{sku}")
    public ResponseEntity<List<InventoryDTO>> getEntryById(@PathVariable Long sku){
        return new ResponseEntity<>(inventoryService.getEntryById(sku),HttpStatus.OK);
    }

    @GetMapping("/warehouse/{warehouseID}")
    public ResponseEntity<List<InventoryDTO>> getEntryByWarehouseId(@PathVariable Long warehouseID){

        return new ResponseEntity<>(inventoryService.getEntryByWarehouseId(warehouseID),HttpStatus.OK);
    }

    @GetMapping("/product/{sku}/warehouse/{warehouseID}/expiry/{expiryDate}")
    public ResponseEntity<InventoryDTO> getEntryByPK(@PathVariable Integer warehouseID, @PathVariable Long sku,@PathVariable Date expiryDate){

        return new ResponseEntity<>(inventoryService.getEntryByPK(warehouseID,sku,expiryDate),HttpStatus.OK);
    }

    @GetMapping("/product-category/{name}")
    public ResponseEntity<List<InventoryDTO>> getEntryByProductCategory(@PathVariable String name){

        return new ResponseEntity<>(inventoryService.getEntryByProductCategory(name),HttpStatus.OK);
    }

    @GetMapping("/product-name/{name}")
    public ResponseEntity<List<InventoryDTO>> getEntryByProductName(@PathVariable String name){

        return new ResponseEntity<>(inventoryService.getEntryByProductName(name),HttpStatus.OK);
    }
    @GetMapping("/lower/{lowerEnd}/higher/{higherEnd}")
    public ResponseEntity<List<InventoryDTO>> getEntryByPrice(@PathVariable Double lowerEnd, @PathVariable Double higherEnd){

        return new ResponseEntity<>(inventoryService.getEntryByPrice(lowerEnd,higherEnd),HttpStatus.OK);
    }



    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteInventory(@RequestBody InventoryDTO inventoryDTO) throws ParseException {
        inventoryService.deleteFromInventory(inventoryDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
