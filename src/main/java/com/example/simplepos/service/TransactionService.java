package com.example.simplepos.service;
import com.example.simplepos.dto.TransactionDTO;
import com.example.simplepos.entity.Order;
import com.example.simplepos.entity.OrderItem;
import com.example.simplepos.entity.Transaction;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final OrderService orderService;
    private final InventoryService inventoryService;


    public TransactionService(TransactionRepository transactionRepository, OrderService orderService, InventoryService inventoryService) {
        this.transactionRepository = transactionRepository;
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }


    public boolean saveTransaction(TransactionDTO transactionDTO) throws ParseException {

        Order order = orderService.getOrder(transactionDTO.getOrderId());
        Transaction transaction = transactionRepository.findById(transactionDTO.getTransactionId()).orElse(null);

        if(transaction != null)
            return false;

        else {
            transaction.setTransactionId(transactionDTO.getTransactionId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// Parse the date and time from the DTO
            transaction.setTransactionDateAndTime(sdf.parse(transactionDTO.getTransactionDateAndTime()));
            transaction.setAmountAfterTax(transactionDTO.getAmountAfterTax());
            transaction.setOrder(order);


            transactionRepository.save(transaction);
            for(OrderItem orderItem : transaction.getOrder().getOrderItems()){

                inventoryService.updateInventoryForTransaction(orderItem.getOrderQuantity(),orderItem.getId().getSKU());

            }

            return true;

        }


    }

    public List<TransactionDTO> getAllTransaction() {

        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

//    public Transaction updateTransaction(Long id, Transaction newTransaction) {
//        return transactionRepository.findById(id).map(transaction -> {
//            transaction.setAmountAfterTax(newTransaction.getAmountAfterTax());
//            transaction.setTransactionDate(newTransaction.getTransactionDate());
//            transaction.setTransactionTime(newTransaction.getTransactionTime());
//            transaction.setEmployeeId(newTransaction.getEmployeeId());
//            return transactionRepository.save(transaction);
//        }).orElseGet(() -> {
//            newTransaction.setTransactionId(id);
//            return transactionRepository.save(newTransaction);
//        });
//    }


}

