package com.kg.alatoo.midtermSpring.mappers;

import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.entities.User;
import org.mapstruct.*;

@Mapper
public interface UserMapper {

    @Mapping(target = "orders", ignore = true) // Ignore mapping for orders (avoid cyclic dependency)
    User userDtoToUser(UserDTO dto);

    @Mapping(target = "orders", ignore = true) // Ignore mapping for orders (avoid cyclic dependency)
    UserDTO userToUserDto(User user);
}

