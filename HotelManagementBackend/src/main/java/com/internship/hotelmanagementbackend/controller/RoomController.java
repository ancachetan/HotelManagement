package com.internship.hotelmanagementbackend.controller;

import com.internship.hotelmanagementbackend.dto.RoomDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/rooms")
public interface RoomController {
    @GetMapping("/{hotelId}/{checkInDate}/{checkOutDate}")
    ResponseEntity<List<RoomDto>> getAllAvailableInSpecifiedInterval(@PathVariable Long hotelId, @PathVariable LocalDate checkInDate, @PathVariable LocalDate checkOutDate);
}
