package com.kg.alatoo.midtermSpring.services;
import com.kg.alatoo.midtermSpring.dto.UserDTO;

import java.util.List;
public interface UserService {
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}

