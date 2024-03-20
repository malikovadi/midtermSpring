package com.kg.alatoo.midtermSpring.services;

import com.kg.alatoo.midtermSpring.entities.User;
import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.repositories.UserRepository;
import com.kg.alatoo.midtermSpring.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceJPATest {

    @Autowired
    private UserServiceJPA userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Test
    void testGetUserById() {
        // Mocking repository behavior
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setUsername("lasdfasd");
        user.setEmail("john.doe@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Mocking mapper behavior
        UserDTO expectedUserDTO = new UserDTO();
        expectedUserDTO.setId(1L);
        expectedUserDTO.setName("John Doe");
        expectedUserDTO.setUsername("lasdfasd");
        expectedUserDTO.setEmail("john.doe@example.com");
        when(userMapper.userToUserDto(user)).thenReturn(expectedUserDTO);

        // Calling service method
        UserDTO actualUserDTO = userService.getUserById(1L);

        // Verifying result
        assertEquals(expectedUserDTO.getId(), actualUserDTO.getId());
        assertEquals(expectedUserDTO.getName(), actualUserDTO.getName());
        assertEquals(expectedUserDTO.getEmail(), actualUserDTO.getEmail());
        assertEquals(expectedUserDTO.getUsername(), actualUserDTO.getUsername());
    }

    // Add more test methods for other service operations
}
