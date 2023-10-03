package com.springbootservices.mapper;

import com.springbootservices.dto.UserDTO;
import com.springbootservices.entity.User;

public class UserMapper {
    //convert User JPA entity into userdto
    public static UserDTO mapToUserDto(User user){
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDTO;
    }

    public static User mapToUser(UserDTO userDTO){
        User user = new User(
          userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail()
        );
        return user;
    }
}
