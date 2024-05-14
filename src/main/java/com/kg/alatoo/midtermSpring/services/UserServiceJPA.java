package com.kg.alatoo.midtermSpring.services;

import com.kg.alatoo.midtermSpring.dto.authorization.RegistrationDTO;
import com.kg.alatoo.midtermSpring.entities.Roles;
import com.kg.alatoo.midtermSpring.exceptions.NotFoundException;
import com.kg.alatoo.midtermSpring.entities.User;
import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.repositories.UserRepository;
import com.kg.alatoo.midtermSpring.mappers.UserMapper;
import com.kg.alatoo.midtermSpring.repositories.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceJPA implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRolesRepository userRolesRepository;


    @Autowired
    public UserServiceJPA(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public UserDTO register(RegistrationDTO registrationDTO) {
        Roles roles = userRolesRepository.findByRoleName(Roles.Name.USER)
                .orElseThrow(() -> new RuntimeException("USER role not found")); // Handle if the role doesn't exist
        User user = User.builder()
                .name(registrationDTO.getName())
                .email(registrationDTO.getEmail())
                .username(registrationDTO.getUsername())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .roles(Set.of(roles))
                .build();
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }


    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO partiallyUpdateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new NotFoundException("User not found with id: " + id);
        }

        // Apply partial updates
        if (userDTO.getName() != null) {
            existingUser.setName(userDTO.getName());
        }
        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }

        existingUser = userRepository.save(existingUser);
        return userMapper.userToUserDto(existingUser);
    }

    @Override
    public Optional<UserDTO> findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        return Optional.of(userMapper.userToUserDto(user));
    }

    @Override
    public UserDTO saveUser(UserDTO dto) {
        Roles userRole = userRolesRepository.findByRoleName(Roles.Name.USER)
                .orElseThrow(() -> new RuntimeException("USER role not found")); // Handle if the role doesn't exist

        User user = userMapper.userDtoToUser(dto);
        user.setRoles(Set.of(userRole));
        user.setPassword(passwordEncoder.encode("password"));
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
