package com.kg.alatoo.midtermSpring.repositories;

import com.kg.alatoo.midtermSpring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods here if needed
    Optional<User> findByUsername(String username);
}
