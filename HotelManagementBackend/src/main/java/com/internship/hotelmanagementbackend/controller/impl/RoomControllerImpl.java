package com.internship.hotelmanagementbackend.controller.impl;

import com.internship.hotelmanagementbackend.controller.RoomController;
import com.internship.hotelmanagementbackend.dto.RoomDto;
import com.internship.hotelmanagementbackend.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomControllerImpl implements RoomController {
    private final RoomService roomService;

    public RoomControllerImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public ResponseEntity<List<RoomDto>> getAllAvailableInSpecifiedInterval(Long hotelId, LocalDate checkInDate, LocalDate checkOutDate) {
        return new ResponseEntity<>(roomService.getAllAvailableInSpecifiedInterval(hotelId, checkInDate, checkOutDate), HttpStatus.OK);
    }
}
