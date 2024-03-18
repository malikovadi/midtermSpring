package com.kg.alatoo.midtermSpring.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_table") // Use a custom table name
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    // Each order references one user
    @ManyToOne
    private User user;

    // Each order can contain multiple products
    @OneToMany(mappedBy = "order")
    private List<Product> products;
}


