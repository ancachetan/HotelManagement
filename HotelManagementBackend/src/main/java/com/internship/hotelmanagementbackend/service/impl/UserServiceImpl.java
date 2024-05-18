package com.internship.hotelmanagementbackend.service.impl;

import com.internship.hotelmanagementbackend.dto.UserDto;
import com.internship.hotelmanagementbackend.dto.UserLoginRequestDto;
import com.internship.hotelmanagementbackend.dto.UserRegisterRequestDto;
import com.internship.hotelmanagementbackend.exception.AlreadyExistingUserException;
import com.internship.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.internship.hotelmanagementbackend.model.User;
import com.internship.hotelmanagementbackend.repository.UserRepository;
import com.internship.hotelmanagementbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.internship.hotelmanagementbackend.util.ExceptionMessages.ALREADY_EXISTING_USER_EXCEPTION_MESSAGE;
import static com.internship.hotelmanagementbackend.util.ExceptionMessages.USER_NOT_FOUND_BY_CREDENTIALS_MESSAGE;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto register(UserRegisterRequestDto userRegisterRequestDto) {
        userRepository.findByUsername(userRegisterRequestDto.getUsername()).ifPresent(user -> {
            throw new AlreadyExistingUserException(ALREADY_EXISTING_USER_EXCEPTION_MESSAGE.formatted(userRegisterRequestDto.getUsername()));
        });
        User user = new User();
        user.setName(userRegisterRequestDto.getName());
        user.setUsername(userRegisterRequestDto.getUsername());
        user.setPassword(userRegisterRequestDto.getPassword());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto login(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByUsernameAndPassword(userLoginRequestDto.getUsername(), userLoginRequestDto.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_BY_CREDENTIALS_MESSAGE.formatted(userLoginRequestDto.getUsername())));
        return modelMapper.map(user, UserDto.class);
    }
}
