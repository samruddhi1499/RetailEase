package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Entity
    @Table(name = "order_items")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class OrderItem {



        @EmbeddedId
        private OrderItemsPKId id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "SKU", referencedColumnName = "SKU", insertable = false, updatable = false)
        private Product product;

        @ManyToOne
        @JoinColumn(name = "orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
        private Order order;

        @Column(nullable = false)
        private Integer orderQuantity;

        private Double pricePerItem;
    }


