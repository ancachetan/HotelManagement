package com.internship.hotelmanagementbackend.service;

import com.internship.hotelmanagementbackend.dto.RoomDto;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<RoomDto> getAllAvailableInSpecifiedInterval(Long hotelId, LocalDate checkInDate, LocalDate checkOutDate);
}
