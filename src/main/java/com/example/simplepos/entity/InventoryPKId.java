package com.example.simplepos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class InventoryPKId implements Serializable {

    @Column(name = "SKU")
    private Long productSKU;

    @Column(name = "warehouseID")
    private Integer warehouseID;

    @Column(name = "expiryDate", nullable = true)
    private Date expiryDate;
}
