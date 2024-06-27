package com.example.simplepos.controller;

import com.example.simplepos.dto.ProductDTO;
import com.example.simplepos.dto.WarehouseDTO;
import com.example.simplepos.service.ProductService;
import com.example.simplepos.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<?> addWarehouse(@RequestBody WarehouseDTO warehouseDTO){

        warehouseService.addWarehouse(warehouseDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllWarehouse(){

        List<WarehouseDTO> warehouseDTOList = warehouseService.getAllWarehouse();

        return new ResponseEntity<>(warehouseDTOList,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateWarehouse(@RequestBody WarehouseDTO warehouseDTO){

        warehouseService.updateWarehouse(warehouseDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWarehouse(@RequestBody WarehouseDTO warehouseDTO){

        warehouseService.deleteWarehouse(warehouseDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
