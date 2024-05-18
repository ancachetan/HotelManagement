package com.internship.hotelmanagementbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internship.hotelmanagementbackend.model.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookingWithDetailsDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;
    private boolean isCanceled;
    private String hotelName;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private Long hotelId;
    private double price;
}
