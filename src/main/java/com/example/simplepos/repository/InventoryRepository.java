package com.example.simplepos.repository;

import com.example.simplepos.entity.Inventory;
import com.example.simplepos.entity.InventoryPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryPKId>{

    @Query(value = "SELECT * FROM inventory WHERE quantity != 0", nativeQuery = true)
    List<Inventory> findByAllNotZero();

    @Query(value = "SELECT * FROM inventory WHERE  SKU= :paramValue", nativeQuery = true)
    List<Inventory> findBySKU(@Param("paramValue") Long paramValue);

    @Query(value = "SELECT * FROM inventory WHERE warehouseID = :paramValue", nativeQuery = true)
    List<Inventory> findByWarehouseID(@Param("paramValue") Long paramValue);

    @Query(value = "SELECT i.* FROM inventory i " +
            "JOIN product p ON i.SKU = p.SKU " +
            "JOIN product_category pc ON p.product_categoryid = pc.product_categoryid " +
            "WHERE pc.category_name LIKE %:paramValue%", nativeQuery = true)
    List<Inventory> findByProductCategory(@Param("paramValue") String paramValue);


    @Query(value = "SELECT i.*" +
            "FROM inventory i " +
            "JOIN product p ON i.SKU = p.SKU "+
            "WHERE p.product_name LIKE %:productName%", nativeQuery = true)
            List<Inventory> findByProductName (
            @Param("productName") String productName
    );

    @Query(value = "SELECT i.* " +
            "FROM inventory i " +
            "JOIN product p ON i.SKU = p.SKU " +
            "WHERE p.product_selling_price BETWEEN :lowerCost AND :upperCost", nativeQuery = true)
    List<Inventory> findBySellingPrice(
            @Param("lowerCost") Double lowerCost,
            @Param("upperCost") Double upperCost
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM inventory WHERE SKU = :productSKU", nativeQuery = true)
    void deleteBySKU(@Param("productSKU") Long productSKU);



}
