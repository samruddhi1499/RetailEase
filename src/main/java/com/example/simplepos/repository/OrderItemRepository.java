package com.example.simplepos.repository;

import com.example.simplepos.entity.OrderItem;
import com.example.simplepos.entity.OrderItemsPKId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemsPKId> {
}
