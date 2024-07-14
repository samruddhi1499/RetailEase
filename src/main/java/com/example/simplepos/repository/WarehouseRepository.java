package com.example.simplepos.repository;

import com.example.simplepos.entity.Inventory;
import com.example.simplepos.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.net.Inet4Address;
import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    @Query(value = "SELECT warehouseid FROM warehouse WHERE warehouse_name = :paramValue", nativeQuery = true)
    Integer findByWarehouseName(@Param("paramValue") String paramValue);
}
