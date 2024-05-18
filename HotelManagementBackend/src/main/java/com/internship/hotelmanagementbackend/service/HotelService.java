package com.internship.hotelmanagementbackend.service;

import com.internship.hotelmanagementbackend.dto.HotelDto;
import com.internship.hotelmanagementbackend.model.Hotel;

import java.util.List;

public interface HotelService {
    List<HotelDto> getAllInSpecifiedRadius(double radius, double latitude, double longitude);
}
