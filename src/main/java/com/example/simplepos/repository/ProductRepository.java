package com.example.simplepos.repository;

import com.example.simplepos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE product_categoryid = :paramValue", nativeQuery = true)
    List<Product> findByCategoryId(@Param("paramValue") Integer paramValue);
}
