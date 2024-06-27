package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Inventory")
@ToString(exclude = {"product"})
public class Inventory {

    @EmbeddedId
    private InventoryPKId id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKU", referencedColumnName = "SKU", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouseID", referencedColumnName = "warehouseID", insertable = false, updatable = false)
    private Warehouse warehouse;


}
