package com.internship.hotelmanagementbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.internship.hotelmanagementbackend.util.ErrorMessages.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookingRequestDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = NULL_CHECK_IN_DATE_ERROR_MESSAGE)
    private LocalDate checkInDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = NULL_CHECK_OUT_DATE_ERROR_MESSAGE)
    private LocalDate checkOutDate;
    @NotNull(message = NULL_ROOM_ID_ERROR_MESSAGE)
    private Long roomId;
    @NotNull(message = NULL_USER_ID_ERROR_MESSAGE)
    private Long userId;
}
