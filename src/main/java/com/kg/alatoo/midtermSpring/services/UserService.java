package com.kg.alatoo.midtermSpring.services;
import com.kg.alatoo.midtermSpring.dto.OrderDTO;
import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.dto.authorization.RegistrationDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
//    UserDTO createUser(UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO partiallyUpdateUser(Long id, UserDTO userDTO);
    Optional<UserDTO> findUserById(Long id);
    UserDTO saveUser(UserDTO dto);
    UserDTO register(RegistrationDTO dto);
}

