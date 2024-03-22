package com.kg.alatoo.midtermSpring.bootstrap;

import com.kg.alatoo.midtermSpring.dto.OrderDTO;
import com.kg.alatoo.midtermSpring.dto.ProductDTO;
import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.services.OrderService;
import com.kg.alatoo.midtermSpring.services.ProductService;
import com.kg.alatoo.midtermSpring.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;

    public InitData(UserService userService, OrderService orderService, ProductService productService) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create 20 users
        for (int i = 0; i < 20; i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName("User" + (i + 1));
            userDTO.setEmail("user" + (i + 1) + "@example.com");
            userDTO.setUsername("user" + (i + 1) + "_username");
            userService.createUser(userDTO);
        }

        // Create orders for each user
        List<UserDTO> users = userService.getAllUsers();
        for (UserDTO user : users) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setDescription("Order for " + user.getName());
            orderDTO.setUserId(user.getId());
            orderService.createOrder(orderDTO);
        }

        // Create products for each order
        List<OrderDTO> orders = orderService.getAllOrders();
        for (OrderDTO order : orders) {
            for (int i = 0; i < 5; i++) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setName("Product" + (i + 1) + " for Order " + order.getId());
                productDTO.setPrice(10.0 * (i + 1));
                productDTO.setOrderId(order.getId());
                productService.createProduct(productDTO);
            }
        }

    }
}