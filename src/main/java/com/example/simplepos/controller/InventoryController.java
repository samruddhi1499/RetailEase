package com.example.simplepos.controller;

import com.example.simplepos.dto.InventoryDTO;
import com.example.simplepos.entity.Inventory;
import com.example.simplepos.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<?> addInventory(@RequestBody InventoryDTO inventoryDTO) {
        inventoryService.addToInventory(inventoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping
    public List<InventoryDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("product/{SKU}")
    public ResponseEntity<?> getEntryById(@PathVariable Long SKU){

        return new ResponseEntity<>(inventoryService.getEntryById(SKU),HttpStatus.OK);
    }

    @GetMapping("warehouse/{warehouseID}")
    public ResponseEntity<?> getEntryByWarehouseId(@PathVariable Long warehouseID){

        return new ResponseEntity<>(inventoryService.getEntryByWarehouseId(warehouseID),HttpStatus.OK);
    }

    @GetMapping("product/{SKU}/warehouse/{warehouseID}")
    public ResponseEntity<?> getEntryByPK(@PathVariable Integer warehouseID, @PathVariable Long SKU){

        return new ResponseEntity<>(inventoryService.getEntryByPK(warehouseID,SKU),HttpStatus.OK);
    }

    @GetMapping("product-category/{name}")
    public ResponseEntity<?> getEntryByProductCategory(@PathVariable String name){

        return new ResponseEntity<>(inventoryService.getEntryByProductCategory(name),HttpStatus.OK);
    }

    @GetMapping("product-name/{name}")
    public ResponseEntity<?> getEntryByProductName(@PathVariable String name){

        return new ResponseEntity<>(inventoryService.getEntryByProductName(name),HttpStatus.OK);
    }
    @GetMapping("lower/{lowerEnd}/higher/{higherEnd}")
    public ResponseEntity<?> getEntryByPrice(@PathVariable Double lowerEnd, @PathVariable Double higherEnd){

        return new ResponseEntity<>(inventoryService.getEntryByPrice(lowerEnd,higherEnd),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateInventory(@RequestBody InventoryDTO inventoryDTO) {
        if(inventoryService.updateToInventory(inventoryDTO))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteInventory(@RequestBody InventoryDTO inventoryDTO) {
        inventoryService.deleteFromInventory(inventoryDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
