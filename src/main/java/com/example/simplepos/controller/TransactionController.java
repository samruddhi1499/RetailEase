package com.example.simplepos.controller;
import com.example.simplepos.dto.TransactionDTO;
import com.example.simplepos.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-transactions")
public class TransactionController {

    private final  TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/all-transactions")
    public List<TransactionDTO> getAllTransaction() {
        return transactionService.getAllTransaction();
    }
}
