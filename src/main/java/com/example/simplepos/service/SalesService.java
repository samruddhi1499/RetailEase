package com.example.simplepos.service;

import com.example.simplepos.dto.SalesDTO;
import com.example.simplepos.entity.OrderItem;
import com.example.simplepos.entity.Sales;
import com.example.simplepos.entity.Transaction;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.SalesRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final TransactionService transactionService;


    public SalesService(SalesRepository salesRepository, @Lazy TransactionService transactionService) {
        this.salesRepository = salesRepository;
        this.transactionService = transactionService;
    }

    public void addSale(Long transactionId){

        Transaction transaction = transactionService.getTransacionbyId(transactionId);
        if(transaction != null){

            Sales sale = new Sales();
            sale.setTransaction(transaction);
            sale.setTransactionDate(transaction.getTransactionDateAndTime());

            Double amountAfterTax = transaction.getAmountAfterTax();
            Double checkAmount = 0.0;
            for(OrderItem orderItem: transaction.getOrder().getOrderItems()){

                checkAmount += orderItem.getProduct().getProductCostPrice() * orderItem.getOrderQuantity();

            }
            sale.setDecision(amountAfterTax > checkAmount ? "Profit": "Loss");
            salesRepository.save(sale);

        }





    }

    public List<SalesDTO> getAllSales(){

        List<Sales> sales = salesRepository.findAll();
        return sales.stream()
                .map(DTOMapper::toDTO)
                .collect(Collectors.toList());


    }


}
