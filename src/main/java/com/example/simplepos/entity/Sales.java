package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private Long id;
    private Date transactionDate;
    private String decision;

    @OneToOne
    @JoinColumn(name = "transactionID", referencedColumnName = "transactionID", nullable = false)
    private Transaction transaction;


}
