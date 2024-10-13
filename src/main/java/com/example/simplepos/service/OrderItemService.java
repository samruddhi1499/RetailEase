package com.example.simplepos.service;

import com.example.simplepos.dto.OrderItemDTO;
import com.example.simplepos.dto.ProductDTOGet;
import com.example.simplepos.entity.OrderItem;
import com.example.simplepos.entity.OrderItemsPKId;
import com.example.simplepos.entity.Product;
import com.example.simplepos.mapper.DTOMapper;
import com.example.simplepos.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;


    public OrderItemService(OrderItemRepository orderItemRepository, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }

    @Transactional
    public boolean saveOrderItem(OrderItemDTO orderItemDto) {



            OrderItem orderItem = new OrderItem();
            Product product = productService.getProductById(orderItemDto.getProductSku());


            OrderItemsPKId orderItemsPKId = new OrderItemsPKId();
            orderItemsPKId.setOrderId(orderItemDto.getOrderId());
            orderItemsPKId.setSKU(orderItemDto.getProductSku());
            orderItem.setOrderQuantity(orderItemDto.getOrderQuantity());
            orderItem.setId(orderItemsPKId);
            orderItem.setPricePerItem(orderItem.getPricePerItem());
            orderItem.setProduct(product);
            orderItemRepository.save(orderItem);

            return true;



    }

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public List<ProductDTOGet> getBestSellingProductSKU() {

        List<ProductDTOGet> allProducts = new ArrayList<>();  // Initialize the list

        for (Long sku : orderItemRepository.findBestSellingProductSKU()) {
            allProducts.add(DTOMapper.toDTOGet(productService.getProductById(sku)));
        }

        return allProducts;

    }

}
