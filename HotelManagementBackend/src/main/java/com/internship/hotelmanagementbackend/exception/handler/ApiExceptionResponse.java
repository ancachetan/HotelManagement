package com.internship.hotelmanagementbackend.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiExceptionResponse {
    private LocalDateTime currentTime;
    private String message;
    private int status;
    private Map<String, String> errors;
}
