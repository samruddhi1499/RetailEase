package com.example.simplepos.service;

import com.example.simplepos.dto.OrderDTO;
import com.example.simplepos.dto.OrderItemDTO;
import com.example.simplepos.entity.Order;
import com.example.simplepos.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    public OrderService(OrderRepository orderRepository, @Lazy OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    public void saveOrder(OrderDTO orderDTO) throws ParseException {

        Order order = new Order();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// Parse the date and time from the DTO
        order.setOrderDateAndTime(sdf.parse(orderDTO.getOrderDateAndTime()));
        order.setOrderId(orderDTO.getOrderId());

        order.setTotalAmount(orderDTO.getTotalAmount());

        orderRepository.save(order);





    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
