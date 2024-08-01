package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;
    private String employeeName;
    private String employeeContact;
    private Double employeeSalary;
    private Date DOJ;
    private String employeeAddress;
    private String employeeEmergency;

    @OneToOne(mappedBy = "employee")
    private User user;
}
