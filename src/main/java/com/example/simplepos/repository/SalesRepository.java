package com.example.simplepos.repository;

import com.example.simplepos.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {

    @Query(value = "SELECT decision FROM sales WHERE transactionId = :paramValue", nativeQuery = true)
    String findSalesByTransaction(@Param("paramValue") Long paramValue);
}
