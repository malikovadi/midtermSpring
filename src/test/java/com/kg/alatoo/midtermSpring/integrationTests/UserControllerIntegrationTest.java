package com.kg.alatoo.midtermSpring.integrationTests;

import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.repositories.UserRepository;
import com.kg.alatoo.midtermSpring.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetAllUsers() throws Exception {
//         Prepare test data
        List<UserDTO> userList = new ArrayList<>();
        userList.add(new UserDTO(1L, "John Doe", "adsfds", "john.doe@example.com"));
        userList.add(new UserDTO(2L, "Jane Smith", "adsfds", "jane.smith@example.com"));

        // Mock service behavior
        when(userService.getAllUsers()).thenReturn(userList);

        // Perform GET request
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].username").value("adsfds"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].username").value("adsfds"))
                .andExpect(jsonPath("$[1].email").value("jane.smith@example.com"));

        // Verify that the service method was called
        verify(userService, times(1)).getAllUsers();
    }
}
