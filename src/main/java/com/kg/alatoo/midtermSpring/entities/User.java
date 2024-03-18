package com.kg.alatoo.midtermSpring.entities;


import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String email;

    // Each user can have multiple orders
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}

