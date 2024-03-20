package com.kg.alatoo.midtermSpring.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;

}
