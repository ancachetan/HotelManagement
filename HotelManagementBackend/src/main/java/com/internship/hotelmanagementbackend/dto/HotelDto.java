package com.internship.hotelmanagementbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HotelDto {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
}
