package com.example.simplepos.controller;
import com.example.simplepos.dto.InventoryDTO;
import com.example.simplepos.dto.TransactionDTO;
import com.example.simplepos.entity.Transaction;
import com.example.simplepos.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api-transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add-transaction")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO transactionDTO) throws ParseException {
        if(! transactionService.saveTransaction(transactionDTO))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/all-transactions")
    public List<TransactionDTO> getAllTransaction() {
        return transactionService.getAllTransaction();
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
//        return ResponseEntity.ok(transactionService.updateTransaction(id, transaction));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
//        transactionService.deleteTransaction(id);
//        return ResponseEntity.ok().build();
//    }
}
