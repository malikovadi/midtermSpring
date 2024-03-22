package com.kg.alatoo.midtermSpring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kg.alatoo.midtermSpring.dto.OrderDTO;
import com.kg.alatoo.midtermSpring.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void testGetAllOrders() throws Exception {
        List<OrderDTO> orderList = new ArrayList<>();
        when(orderService.getAllOrders()).thenReturn(orderList);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    public void testGetOrderById() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setDescription("Test Order");

        when(orderService.getOrderById(1L)).thenReturn(orderDTO);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("Test Order"));

        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    public void testCreateOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDescription("Test Order");

        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(orderDTO);

        when(orderService.createOrder(any(OrderDTO.class))).thenReturn(orderDTO);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value("Test Order"));

        verify(orderService, times(1)).createOrder(any(OrderDTO.class));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setDescription("Updated Order");

        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(orderDTO);

        when(orderService.updateOrder(eq(1L), any(OrderDTO.class))).thenReturn(orderDTO);

        mockMvc.perform(put("/api/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description").value("Updated Order"));

        verify(orderService, times(1)).updateOrder(eq(1L), any(OrderDTO.class));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).deleteOrder(1L);
    }
}
