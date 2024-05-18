package com.internship.hotelmanagementbackend.dto;

import com.internship.hotelmanagementbackend.util.ErrorMessages;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRegisterRequestDto {
    private static final String NAME_VALIDATION_REGEX = "^[a-zA-Z ]{4,20}$";
    private static final String USERNAME_VALIDATION_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]{2,5}\\.[A-Za-z]{2,4}$";
    private static final String PASSWORD_VALIDATION_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[!@#$%^&*.]).{7,}$";

    @Pattern(regexp = NAME_VALIDATION_REGEX, message = ErrorMessages.INVALID_NAME_FORMAT_ERROR_MESSAGE)
    private String name;
    @Pattern(regexp = USERNAME_VALIDATION_REGEX, message = ErrorMessages.INVALID_EMAIL_FORMAT_ERROR_MESSAGE)
    private String username;
    @Pattern(regexp = PASSWORD_VALIDATION_REGEX, message = ErrorMessages.INVALID_PASSWORD_FORMAT_ERROR_MESSAGE)
    private String password;
}
