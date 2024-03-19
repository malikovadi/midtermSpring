package com.kg.alatoo.midtermSpring.mappers;

import com.kg.alatoo.midtermSpring.dto.OrderDTO;
import com.kg.alatoo.midtermSpring.entities.Order;
import com.kg.alatoo.midtermSpring.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testOrderDtoToOrder() {
        // Given
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDescription("Test Order");
        orderDTO.setUserId(123L);

        // When
        Order order = orderMapper.orderDtoToOrder(orderDTO);

        // Then
        assertNotNull(order);
        assertEquals("Test Order", order.getDescription());
        assertEquals(123L, order.getUser().getId());
    }

    @Test
    public void testOrderToOrderDto() {
        // Given
        Order order = new Order();
        order.setDescription("Test Order");
        User user = new User();
        user.setId(123L);
        order.setUser(user);

        // When
        OrderDTO orderDTO = orderMapper.orderToOrderDto(order);

        // Then
        assertNotNull(orderDTO);
        assertEquals("Test Order", orderDTO.getDescription());
        assertEquals(123L, orderDTO.getUserId());
    }
}
