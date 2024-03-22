package com.kg.alatoo.midtermSpring.controllers;

import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.exceptions.NotFoundException;
import com.kg.alatoo.midtermSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
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
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // PUT update existing user
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Validated @PathVariable("id") Long id,@Validated @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            throw new NotFoundException("Couldn't update user with id: " + id);
        }
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
