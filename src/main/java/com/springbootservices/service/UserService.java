package com.springbootservices.service;

import com.springbootservices.dto.UserDTO;
import com.springbootservices.entity.User;

import java.util.List;

public interface UserService {
    public UserDTO createUser(UserDTO user);
    public UserDTO getUserById(Long id);
    public List<UserDTO> getALlUser();

    public UserDTO updateUser(User user);
    public void deleteUser(Long id);
}
