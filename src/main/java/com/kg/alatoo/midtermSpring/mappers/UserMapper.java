package com.kg.alatoo.midtermSpring.mappers;

import com.kg.alatoo.midtermSpring.dto.UserDTO;
import com.kg.alatoo.midtermSpring.entities.User;
import org.mapstruct.*;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User userDtoToUser(UserDTO dto);

    UserDTO userToUserDto(User user);
}

