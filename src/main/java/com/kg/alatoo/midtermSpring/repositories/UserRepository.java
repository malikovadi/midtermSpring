package com.kg.alatoo.midtermSpring.repositories;

import com.kg.alatoo.midtermSpring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods here if needed
}
