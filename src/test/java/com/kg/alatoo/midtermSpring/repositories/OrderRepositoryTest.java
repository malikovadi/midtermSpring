package com.kg.alatoo.midtermSpring.repositories;

import com.kg.alatoo.midtermSpring.entities.Order;
import com.kg.alatoo.midtermSpring.entities.User;
import com.kg.alatoo.midtermSpring.repositories.OrderRepository;
import com.kg.alatoo.midtermSpring.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository; // Assuming UserRepository is also needed for OrderRepository

    @Test
    public void testSaveAndFindOrder() {
        // Create a user entity
        User user = new User();
        user.setName("John Doe");
        user.setUsername("johndoe");
        user.setEmail("john@example.com");
        userRepository.save(user);

        // Create an order entity
        Order order = new Order();
        order.setDescription("Sample order");
        order.setUser(user);

        // Save the order entity
        orderRepository.save(order);

        // Find the saved order entity
        Order foundOrder = orderRepository.findById(order.getId()).orElse(null);

        // Assert that the found order is not null and has the correct properties
        assertThat(foundOrder).isNotNull();
        assertThat(foundOrder.getDescription()).isEqualTo("Sample order");
        assertThat(foundOrder.getUser()).isEqualTo(user);
    }
}

