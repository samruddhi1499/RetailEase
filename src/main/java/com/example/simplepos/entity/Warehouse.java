package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouseID")
    private Integer warehouseID;
    private String warehouseName;

    @OneToMany(mappedBy = "warehouse")
    private List<Inventory> inventory;

    public Warehouse(Integer warehouseId) {
    }
}
