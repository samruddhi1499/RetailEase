package com.example.simplepos.repository;

import com.example.simplepos.entity.Inventory;
import com.example.simplepos.entity.InventoryPKId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryPKId>{
}
