package com.example.simplepos.service;

import com.example.simplepos.dto.SalesDTO;
import com.example.simplepos.dto.TransactionDTO;
import com.example.simplepos.entity.Order;
import com.example.simplepos.entity.OrderItem;
import com.example.simplepos.entity.Transaction;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final OrderService orderService;
    private final InventoryService inventoryService;

    private final SalesService salesService;


    public TransactionService(TransactionRepository transactionRepository, OrderService orderService, InventoryService inventoryService, SalesService salesService) {
        this.transactionRepository = transactionRepository;
        this.orderService = orderService;
        this.inventoryService = inventoryService;
        this.salesService = salesService;
    }


    @Transactional
    public boolean saveTransaction(TransactionDTO transactionDTO) throws ParseException {

        Order order = orderService.getOrder(transactionDTO.getOrderId());

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionDTO.getTransactionId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        transaction.setTransactionDateAndTime(sdf.parse(sdf.format(new Date())));
        transaction.setAmountAfterTax(transactionDTO.getAmountAfterTax());
        transaction.setEmployeeId(transactionDTO.getEmployeeId());
        transaction.setOrder(order);


        transactionRepository.save(transaction);
        for (OrderItem orderItem : transaction.getOrder().getOrderItems()) {

            inventoryService.updateInventoryForTransaction(orderItem.getOrderQuantity(), orderItem.getId().getSKU());

        }

        salesService.addSale(transaction.getTransactionId());

        return true;


    }

    public List<TransactionDTO> getAllTransaction() {

        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Transaction getTransacionbyId(Long transactionId) {

        return transactionRepository.findById(transactionId).orElse(null);
    }



}

