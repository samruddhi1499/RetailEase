package com.example.simplepos.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {

    private int inventroyID;

    private String UPC;

    private String SKU;

    private int warehouseID;

    private long quantity;

}
