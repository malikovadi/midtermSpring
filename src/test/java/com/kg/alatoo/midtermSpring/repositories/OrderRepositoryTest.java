package com.kg.alatoo.midtermSpring.repositories;

import com.kg.alatoo.midtermSpring.entities.Order;
import com.kg.alatoo.midtermSpring.entities.User;
import com.kg.alatoo.midtermSpring.repositories.OrderRepository;
import com.kg.alatoo.midtermSpring.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setDescription("Test Order");

        Order savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder.getId());
        assertEquals("Test Order", savedOrder.getDescription());
    }

    @Test
    public void testFindOrderById() {
        Order order = new Order();
        order.setDescription("Test Order");

        Order savedOrder = orderRepository.save(order);
        Optional<Order> optionalOrder = orderRepository.findById(savedOrder.getId());
        assertTrue(optionalOrder.isPresent());
        assertEquals("Test Order", optionalOrder.get().getDescription());
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order();
        order.setDescription("Test Order");

        Order savedOrder = orderRepository.save(order);
        savedOrder.setDescription("Updated Order");

        Order updatedOrder = orderRepository.save(savedOrder);
        assertEquals(savedOrder.getId(), updatedOrder.getId());
        assertEquals("Updated Order", updatedOrder.getDescription());
    }

    @Test
    public void testDeleteOrder() {
        Order order = new Order();
        order.setDescription("Test Order");

        Order savedOrder = orderRepository.save(order);
        orderRepository.delete(savedOrder);

        Optional<Order> optionalOrder = orderRepository.findById(savedOrder.getId());
        assertFalse(optionalOrder.isPresent());
    }
}

