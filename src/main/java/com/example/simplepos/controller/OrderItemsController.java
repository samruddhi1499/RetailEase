package com.example.simplepos.controller;

import com.example.simplepos.dto.OrderItemDTO;
import com.example.simplepos.entity.OrderItem;
import com.example.simplepos.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    private final OrderItemService orderItemService;

    public OrderItemsController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

//    @PostMapping
//    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItemDto ){
//        return ResponseEntity.ok(orderItemService.saveOrderItem(orderItemDto));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
//        OrderItem orderItem = orderItemService.getOrderItem(id);
//        if (orderItem != null) {
//            return ResponseEntity.ok(orderItem);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
//       // return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItem));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {
//        orderItemService.deleteOrderItem(id);
//        return ResponseEntity.ok().build();
//    }
}
