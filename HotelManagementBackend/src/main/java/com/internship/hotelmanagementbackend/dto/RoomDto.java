package com.internship.hotelmanagementbackend.dto;

import com.internship.hotelmanagementbackend.model.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RoomDto {
    private Long id;
    private int roomNumber;
    @Enumerated(EnumType.STRING)
    private RoomType type;
    private double price;
}
