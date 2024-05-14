package com.kg.alatoo.midtermSpring.dto;
import com.kg.alatoo.midtermSpring.entities.Roles;
import com.kg.alatoo.midtermSpring.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username must be between 4 and 20 characters")
    private String username;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

}
