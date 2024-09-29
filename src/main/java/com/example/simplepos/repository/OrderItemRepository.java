package com.example.simplepos.repository;

import com.example.simplepos.entity.OrderItem;
import com.example.simplepos.entity.OrderItemsPKId;
import com.example.simplepos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemsPKId> {

    @Query(value = "SELECT * FROM order_items WHERE order_id = :paramValue", nativeQuery = true)
    List<OrderItem> findByOrderId(@Param("paramValue") Long paramValue);
}
