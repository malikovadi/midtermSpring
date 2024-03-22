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
import static org.mockito.Mockito.*;

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
        UserDTO expectedUserDTO = new UserDTO(user);
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

    @Test
    void testGetAllUsers() {
        // Mocking repository behavior
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setUsername("johndoe");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Jane Doe");
        user2.setUsername("janedoe");
        user2.setEmail("jane.doe@example.com");

        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        // Mocking mapper behavior
        when(userMapper.userToUserDto(user1)).thenReturn(new UserDTO(user1));
        when(userMapper.userToUserDto(user2)).thenReturn(new UserDTO(user2));

        // Calling service method
        List<UserDTO> actualUserDTOList = userService.getAllUsers();

        // Verifying result
        assertEquals(userList.size(), actualUserDTOList.size());
        for (int i = 0; i < userList.size(); i++) {
            User expectedUser = userList.get(i);
            UserDTO actualUserDTO = actualUserDTOList.get(i);
            assertEquals(expectedUser.getId(), actualUserDTO.getId());
            assertEquals(expectedUser.getName(), actualUserDTO.getName());
            assertEquals(expectedUser.getUsername(), actualUserDTO.getUsername());
            assertEquals(expectedUser.getEmail(), actualUserDTO.getEmail());
        }
    }

    @Test
    void testDeleteUser() {
        // Calling service method
        userService.deleteUser(1L);

        // Verifying repository method invocation
        verify(userRepository, times(1)).deleteById(1L);
    }

}
