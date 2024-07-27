package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

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

//    // Mapping expiryDate from the composite key
//    @Column(name = "expiryDate", insertable = false, updatable = false, nullable = true)
//    private Date expiryDate;

//    public Inventory(InventoryPKId id, int quantity, Product product, Warehouse warehouse) {
//        this.id = id;
//        this.quantity = quantity;
//        this.product = product;
//        this.warehouse = warehouse;
////        this.expiryDate = id.getExpiryDate();
//    }
}
