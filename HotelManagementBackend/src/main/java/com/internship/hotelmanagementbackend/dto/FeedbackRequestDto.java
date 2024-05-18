package com.internship.hotelmanagementbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.internship.hotelmanagementbackend.util.ErrorMessages.NULL_HOTEL_ID_ERROR_MESSAGE;
import static com.internship.hotelmanagementbackend.util.ErrorMessages.NULL_USER_ID_ERROR_MESSAGE;
import static com.internship.hotelmanagementbackend.util.ErrorMessages.BLANK_COMMENT_ERROR_MESSAGE;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FeedbackRequestDto {
    @NotBlank(message = BLANK_COMMENT_ERROR_MESSAGE)
    private String comment;
    @NotNull(message = NULL_HOTEL_ID_ERROR_MESSAGE)
    private Long hotelId;
    @NotNull(message = NULL_USER_ID_ERROR_MESSAGE)
    private Long userId;
}
