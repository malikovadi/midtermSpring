package com.kg.alatoo.midtermSpring.repositories;

import com.kg.alatoo.midtermSpring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can add custom query methods here if needed
}

