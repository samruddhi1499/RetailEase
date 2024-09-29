package com.example.simplepos.controller;

import com.example.simplepos.dto.OrderDTO;
import com.example.simplepos.dto.OrderItemDTO;
import com.example.simplepos.entity.Order;
import com.example.simplepos.service.OrderItemService;
import com.example.simplepos.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api-orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @PostMapping("/add-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) throws ParseException {
        if(! orderService.saveOrder(orderDTO))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
//        Order order = orderService.getOrder(id);
//        if (order != null) {
//            return ResponseEntity.ok(order);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
