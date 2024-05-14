package com.kg.alatoo.midtermSpring.controllers;

import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.exceptions.NotFoundException;
import com.kg.alatoo.midtermSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET all users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@Validated @PathVariable("id") Long id) {
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            throw new NotFoundException("User not found with id: " + id);
        }
    }

    // POST create new user
    @PostMapping
    public UserDTO createUser(@Validated @RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    // PUT update existing user
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @Validated @RequestBody UserDTO userDTO) {
        userService.findUserById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        userDTO.setId(id);
        return userService.saveUser(userDTO);
    }

    // PATCH /api/users/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> partiallyUpdateUser(@Validated @PathVariable Long id,@Validated @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.partiallyUpdateUser(id, userDTO);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            throw new NotFoundException("Couldn't update user with id: " + id);
        }
    }

    // DELETE user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Validated @PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
