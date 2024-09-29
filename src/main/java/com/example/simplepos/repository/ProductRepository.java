package com.example.simplepos.repository;

import com.example.simplepos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE product_categoryid = :paramValue", nativeQuery = true)
    List<Product> findByCategoryId(@Param("paramValue") Integer paramValue);

    @Query(value = "SELECT * FROM product WHERE discount_id = :paramValue", nativeQuery = true)
    List<Product> findByDiscountId(@Param("paramValue") Integer paramValue);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET discount_id = :newValue, product_discount_price = 0 WHERE discount_id = :paramValue", nativeQuery = true)
    void deleteDiscountId(@Param("newValue") Integer newValue, @Param("paramValue") Integer paramValue);

}
