package com.internship.hotelmanagementbackend.service;

import com.internship.hotelmanagementbackend.dto.UserLoginRequestDto;
import com.internship.hotelmanagementbackend.dto.UserRegisterRequestDto;
import com.internship.hotelmanagementbackend.dto.UserDto;

public interface UserService {
    UserDto register(UserRegisterRequestDto userRegisterRequestDto);
    UserDto login(UserLoginRequestDto userLoginRequestDto);
}
