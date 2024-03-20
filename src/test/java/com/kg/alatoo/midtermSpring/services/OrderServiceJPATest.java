package com.kg.alatoo.midtermSpring.services;

import com.kg.alatoo.midtermSpring.entities.Order;
import com.kg.alatoo.midtermSpring.dto.OrderDTO;
import com.kg.alatoo.midtermSpring.entities.User;
import com.kg.alatoo.midtermSpring.repositories.OrderRepository;
import com.kg.alatoo.midtermSpring.mappers.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceJPATest {

    @Autowired
    private OrderServiceJPA orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderMapper orderMapper;

    @Test
    void testGetOrderById() {
        // Mocking repository behavior
        Order order = new Order();
        order.setId(1L);
        order.setDescription("Test Order");
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Mocking mapper behavior
        OrderDTO expectedOrderDTO = new OrderDTO();
        expectedOrderDTO.setId(1L);
        expectedOrderDTO.setDescription("Test Order");
        when(orderMapper.orderToOrderDto(order)).thenReturn(expectedOrderDTO);

        // Calling service method
        OrderDTO actualOrderDTO = orderService.getOrderById(1L);

        // Verifying result
        assertEquals(expectedOrderDTO.getId(), actualOrderDTO.getId());
        assertEquals(expectedOrderDTO.getDescription(), actualOrderDTO.getDescription());
    }

    // Add more test methods for other service operations
}
