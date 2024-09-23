package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "orderId")
    private Long orderId;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private Date orderDateAndTime;


    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}
