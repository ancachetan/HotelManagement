package com.internship.hotelmanagementbackend.controller.impl;

import com.internship.hotelmanagementbackend.controller.HotelController;
import com.internship.hotelmanagementbackend.dto.HotelDto;
import com.internship.hotelmanagementbackend.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HotelControllerImpl implements HotelController {
    private final HotelService hotelService;

    public HotelControllerImpl(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public ResponseEntity<List<HotelDto>> getAllInSpecifiedRadius(double radius, double latitude, double longitude) {
        return new ResponseEntity<>(this.hotelService.getAllInSpecifiedRadius(radius, latitude, longitude), HttpStatus.OK);
    }
}
