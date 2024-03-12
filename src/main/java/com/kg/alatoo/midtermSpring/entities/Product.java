package com.kg.alatoo.midtermSpring.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}

