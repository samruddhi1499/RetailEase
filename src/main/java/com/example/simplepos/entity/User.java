package com.example.simplepos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String username;
    private String password;
    private String role; // Moved role field here

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
