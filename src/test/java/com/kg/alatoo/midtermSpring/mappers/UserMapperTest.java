package com.kg.alatoo.midtermSpring.mappers;

import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserDtoToUser() {
        // Given
        UserDTO userDTO = new UserDTO(user);
        userDTO.setName("John Doe");
        userDTO.setEmail("john.doe@example.com");

        // When
        User user = userMapper.userDtoToUser(userDTO);

        // Then
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testUserToUserDto() {
        // Given
        User user = new User();
        user.setName("Jane Smith");
        user.setEmail("jane.smith@example.com");

        // When
        UserDTO userDTO = userMapper.userToUserDto(user);

        // Then
        assertNotNull(userDTO);
        assertEquals("Jane Smith", userDTO.getName());
        assertEquals("jane.smith@example.com", userDTO.getEmail());
    }
}