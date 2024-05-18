package com.internship.hotelmanagementbackend.controller;

import com.internship.hotelmanagementbackend.dto.UserLoginRequestDto;
import com.internship.hotelmanagementbackend.dto.UserRegisterRequestDto;
import com.internship.hotelmanagementbackend.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public interface UserController {
    @PostMapping("/register")
    ResponseEntity<UserDto> register(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto);

    @PostMapping("/login")
    ResponseEntity<UserDto> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto);
}
