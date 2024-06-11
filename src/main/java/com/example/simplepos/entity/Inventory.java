package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(InventoryPKId.class)
public class Inventory {

    @EmbeddedId
    private InventoryPKId id;

    private int quantity;

    @ManyToOne
    @MapsId("SKU")
    @JoinColumn(name = "SKU")
    private Product product;

    @ManyToOne
    @MapsId("warehouseID")
    @JoinColumn(name = "warehouseID")
    private Warehouse warehouse;

}
