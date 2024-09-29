package com.example.simplepos.service;

import com.example.simplepos.dto.OrderDTO;
import com.example.simplepos.dto.OrderItemDTO;
import com.example.simplepos.dto.TransactionDTO;
import com.example.simplepos.entity.Order;
import com.example.simplepos.repository.OrderRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final TransactionService transactionService;

    public OrderService(OrderRepository orderRepository, @Lazy OrderItemService orderItemService, @Lazy TransactionService transactionService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.transactionService = transactionService;
    }

    @Transactional
    public boolean saveOrder(OrderDTO orderDTO) throws ParseException {

        Order order = new Order();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        order.setOrderDateAndTime(sdf.parse(sdf.format(new Date())));
        order.setOrderId(orderDTO.getOrderId());

        order.setTotalAmount(orderDTO.getTotalAmount());

        orderRepository.save(order);
        for(OrderItemDTO orderItemDTO : orderDTO.getOrderItems()){

            orderItemDTO.setOrderId(order.getOrderId());
            if(!orderItemService.saveOrderItem(orderItemDTO))
                return false;
        }
        order.setOrderItems(orderItemService.getOrderItemsByOrderId(order.getOrderId()));
        orderRepository.save(order);
        transactionService.saveTransaction(new TransactionDTO(orderDTO.getAmountAfterTax(),orderDTO.getEmployeeId(), order.getOrderId()));
        return true;


    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
