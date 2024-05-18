package com.internship.hotelmanagementbackend.controller.impl;

import com.internship.hotelmanagementbackend.controller.UserController;
import com.internship.hotelmanagementbackend.dto.UserLoginRequestDto;
import com.internship.hotelmanagementbackend.dto.UserRegisterRequestDto;
import com.internship.hotelmanagementbackend.dto.UserDto;
import com.internship.hotelmanagementbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDto> register(UserRegisterRequestDto userRegisterRequestDto) {
        return new ResponseEntity<>(userService.register(userRegisterRequestDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> login(UserLoginRequestDto userLoginRequestDto) {
        return new ResponseEntity<>(userService.login(userLoginRequestDto), HttpStatus.OK);
    }
}
