package com.example.simplepos.service;

import com.example.simplepos.dto.OrderItemDTO;
import com.example.simplepos.entity.Order;
import com.example.simplepos.entity.OrderItem;
import com.example.simplepos.entity.OrderItemsPKId;
import com.example.simplepos.entity.Product;
import com.example.simplepos.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final OrderService orderService;

    public OrderItemService(OrderItemRepository orderItemRepository, ProductService productService, OrderService orderService) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    public boolean saveOrderItem(OrderItemDTO orderItemDto) {

        OrderItem orderItem = orderItemRepository.findById(new OrderItemsPKId(orderItemDto.getProductSku(), orderItemDto.getOrderId())).orElse(null);
        if(orderItem != null){

            return false;
        }
        else {

            orderItem = new OrderItem();
            Product product = productService.getProductById(orderItemDto.getProductSku());
            //Order order = orderService.getOrder(orderItemDto.getOrderId());


            OrderItemsPKId orderItemsPKId = new OrderItemsPKId();
            orderItemsPKId.setOrderId(orderItemDto.getOrderId());
            orderItemsPKId.setSKU(orderItemDto.getProductSku());
            orderItem.setOrderQuantity(orderItemDto.getOrderQuantity());
            orderItem.setId(orderItemsPKId);
            orderItem.setPricePerItem(orderItem.getPricePerItem());
            //orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItemRepository.save(orderItem);

            return true;

        }

    }

//    public OrderItem getOrderItem(Long id) {
//        return orderItemRepository.findById(id).orElse(null);
//    }
//
////    public OrderItem updateOrderItem(Long id, OrderItem newItem) {
////        return orderItemRepository.findById(id).map(orderItem -> {
////            orderItem.setSKU(newItem.getSKU());
////            orderItem.setOrderQuantity(newItem.getOrderQuantity());
////            orderItem.setPricePerItem(newItem.getPricePerItem());
////            return orderItemRepository.save(orderItem);
////        }).orElseGet(() -> {
////            newItem.setId(id);
////            return orderItemRepository.save(newItem);
////        });
////    }
//
//    public void deleteOrderItem(Long id) {
//        orderItemRepository.deleteById(id);
//    }
}
