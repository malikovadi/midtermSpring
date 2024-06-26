package com.kg.alatoo.midtermSpring.services;

import com.kg.alatoo.midtermSpring.exceptions.NotFoundException;
import com.kg.alatoo.midtermSpring.entities.Order;
import com.kg.alatoo.midtermSpring.dto.OrderDTO;
import com.kg.alatoo.midtermSpring.repositories.OrderRepository;
import com.kg.alatoo.midtermSpring.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceJPA implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceJPA(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().
                map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.orderDtoToOrder(orderDTO);
        order = orderRepository.save(order);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder == null) {
            throw new NotFoundException("Couldn't update order with id: " + id);
        }
        existingOrder.setDescription(orderDTO.getDescription());
        // Update other fields as needed
        existingOrder = orderRepository.save(existingOrder);
        return orderMapper.orderToOrderDto(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDTO partiallyUpdateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder == null) {
            throw new NotFoundException("Couldn't update order with id: " + id);
        }

        // Apply partial updates
        if (orderDTO.getDescription() != null) {
            existingOrder.setDescription(orderDTO.getDescription());
        }

        existingOrder = orderRepository.save(existingOrder);
        return orderMapper.orderToOrderDto(existingOrder);
    }
}
