package com.example.simplepos.controller;

import com.example.simplepos.dto.SalesDTO;
import com.example.simplepos.service.SalesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api-sales")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("get-all-sales")
    public ResponseEntity<List<SalesDTO>> getAllProduct(){

        List<SalesDTO> salesDTOList = salesService.getAllSales();

        return new ResponseEntity<>(salesDTOList, HttpStatus.OK);
    }

}
