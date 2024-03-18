package com.kg.alatoo.midtermSpring.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String description;
    private Long userId;
}
