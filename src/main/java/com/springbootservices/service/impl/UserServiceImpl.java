package com.springbootservices.service.impl;

import com.springbootservices.dto.UserDTO;
import com.springbootservices.entity.User;
import com.springbootservices.exception.EmailAlreadyExistsException;
import com.springbootservices.exception.ResourceNotFoundException;
import com.springbootservices.repository.UserRepository;
import com.springbootservices.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public UserDTO createUser(UserDTO userDto) {
//        User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto,User.class);
       // User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exist for this User");
        }
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
       // return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User","id",id)
        );
//        return UserMapper.mapToUserDto(user.get());
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getALlUser() {
        List<User> listOfUsers =  userRepository.findAll();
      //  List<UserDTO> listOfDto = new ArrayList<>();
//        for(User user:listOfUsers){
//            listOfDto.add(UserMapper.mapToUserDto(user));
//        }
//        return listOfDto;
//        return listOfUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return listOfUsers.stream().map((user)->modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                ()->new ResourceNotFoundException("User","id",user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        //return UserMapper.mapToUserDto(userRepository.save(existingUser));
        return modelMapper.map(userRepository.save(existingUser),UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User","id",id)
        );
        userRepository.deleteById(id);
    }

}
