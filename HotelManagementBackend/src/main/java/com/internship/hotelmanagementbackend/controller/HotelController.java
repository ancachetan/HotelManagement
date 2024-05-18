package com.internship.hotelmanagementbackend.controller;

import com.internship.hotelmanagementbackend.dto.HotelDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/hotels")
public interface HotelController {
    @GetMapping("/{radius}/{latitude}/{longitude}")
    ResponseEntity<List<HotelDto>> getAllInSpecifiedRadius(@PathVariable double radius, @PathVariable double latitude, @PathVariable double longitude);
}
