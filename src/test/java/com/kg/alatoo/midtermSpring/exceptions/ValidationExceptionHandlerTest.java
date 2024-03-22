package com.kg.alatoo.midtermSpring.exceptions;

import com.kg.alatoo.midtermSpring.controllers.UserController;
import com.kg.alatoo.midtermSpring.dto.OrderDTO;
import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.services.OrderService;
import com.kg.alatoo.midtermSpring.services.ProductService;
import com.kg.alatoo.midtermSpring.services.UserService;
import org.junit.jupiter.api.Test;
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

@WebMvcTest(UserController.class)
public class ValidationExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetAllUsers() throws Exception {
        // Mocking service behavior
        List<UserDTO> userList = new ArrayList<>();
        // Add some user DTOs to the list
        when(userService.getAllUsers()).thenReturn(userList);

        // Perform GET request
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        // Verify that userService.getAllUsers() was called once
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() throws Exception {
        // Mocking service behavior
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John Doe");
        // Mocking the behavior of the service method to return a specific user by ID
        when(userService.getUserById(1L)).thenReturn(userDTO);

        // Perform GET request to retrieve a user by ID
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));

        // Verify that userService.getUserById(1L) was called once
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Mocking service behavior
        doNothing().when(userService).deleteUser(1L); // Mocking the behavior of the service method to delete a user by ID

        // Perform DELETE request to delete a user by ID
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        // Verify that userService.deleteUser(1L) was called once
        verify(userService, times(1)).deleteUser(1L);
    }
}
