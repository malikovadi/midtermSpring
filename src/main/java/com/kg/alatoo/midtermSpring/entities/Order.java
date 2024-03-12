package com.kg.alatoo.midtermSpring.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    private List<Product> products;
}


