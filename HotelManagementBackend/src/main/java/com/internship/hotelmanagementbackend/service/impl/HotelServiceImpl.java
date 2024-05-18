package com.internship.hotelmanagementbackend.service.impl;

import com.internship.hotelmanagementbackend.dto.HotelDto;
import com.internship.hotelmanagementbackend.model.Hotel;
import com.internship.hotelmanagementbackend.repository.HotelRepository;
import com.internship.hotelmanagementbackend.service.HotelService;
import com.internship.hotelmanagementbackend.util.LocationManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final LocationManager locationManager;
    private final ModelMapper modelMapper;

    public HotelServiceImpl(HotelRepository hotelRepository, LocationManager locationManager, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.locationManager = locationManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<HotelDto> getAllInSpecifiedRadius(double radius, double latitude, double longitude) {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .filter(hotel -> locationManager.isLocationInRadius(latitude, longitude, hotel.getLatitude(), hotel.getLongitude(), radius))
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());
    }
}
