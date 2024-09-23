package com.example.simplepos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class OrderItemsPKId implements Serializable {

    @Column(name = "SKU")
    private Long SKU;

    @Column(name = "orderId")
    private Long orderId;
}
