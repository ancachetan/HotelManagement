package com.internship.hotelmanagementbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.internship.hotelmanagementbackend.util.ErrorMessages.BLANK_PASSWORD_ERROR_MESSAGE;
import static com.internship.hotelmanagementbackend.util.ErrorMessages.BLANK_USERNAME_ERROR_MESSAGE;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserLoginRequestDto {
    @NotBlank(message = BLANK_USERNAME_ERROR_MESSAGE)
    private String username;
    @NotBlank(message = BLANK_PASSWORD_ERROR_MESSAGE)
    private String password;
}
