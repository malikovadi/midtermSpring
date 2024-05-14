package com.kg.alatoo.midtermSpring.bootstrap;


import com.kg.alatoo.midtermSpring.entities.Order;
import com.kg.alatoo.midtermSpring.entities.Product;
import com.kg.alatoo.midtermSpring.entities.Roles;
import com.kg.alatoo.midtermSpring.entities.User;
import com.kg.alatoo.midtermSpring.repositories.OrderRepository;
import com.kg.alatoo.midtermSpring.repositories.ProductRepository;
import com.kg.alatoo.midtermSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
    public class InitData implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) {
            User admin = User.builder()
                    .name("Adilet")
                    .username("admin")
                    .email("admin@gmail.com")
                    .roles(Set.of(Roles.ADMIN, Roles.STAFF))
                    .password(passwordEncoder.encode("password"))
                    .build();
            User staff = User.builder()
                    .name("Argen")
                    .username("staff")
                    .email("staff@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .roles(Set.of(Roles.STAFF))
                    .build();
            userRepository.saveAll(List.of(admin, staff));

            try {
                // Create users
                Set<User> users = new HashSet<>();
                for (int i = 0; i < 50; i++) {
                    User user = User.builder()
                            .name("User " + i)
                            .username("user" + i)
                            .email("user" + i + "@example.com")
                            .roles(Set.of(Roles.USER))
                            .password(passwordEncoder.encode("password"))
                            .build();
                    users.add(user);
                }
                userRepository.saveAll(users);

                // Create orders for each user
                Set<Order> orders = new HashSet<>();
                for (User user : users) {
                    Order order = Order.builder()
                            .user(user)
                            .description("Order for " + user.getName())
                            .build();
                    orders.add(order);
                }
                orderRepository.saveAll(orders);

                // Create products for each order
                Set<Product> products = new HashSet<>();
                for (Order order : orders) {
                    for (int i = 0; i < 5; i++) {
                        Product product = Product.builder()
                                .order(order)
                                .name("Product " + (i + 1) + " for Order " + order.getId())
                                .price(10.0 * (i + 1))
                                .build();
                        products.add(product);
                    }
                }
                productRepository.saveAll(products);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
