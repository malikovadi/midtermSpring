package com.kg.alatoo.midtermSpring.services;

import com.kg.alatoo.midtermSpring.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrders();
    OrderDTO createOrder(OrderDTO userDTO);
    OrderDTO updateOrder(Long id, OrderDTO userDTO);
    void deleteOrder(Long id);

    OrderDTO partiallyUpdateOrder(Long id, OrderDTO orderDTO);
}
