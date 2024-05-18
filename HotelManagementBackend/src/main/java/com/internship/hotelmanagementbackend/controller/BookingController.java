package com.internship.hotelmanagementbackend.controller;


import com.internship.hotelmanagementbackend.dto.BookingDto;
import com.internship.hotelmanagementbackend.dto.BookingRequestDto;
import com.internship.hotelmanagementbackend.dto.BookingWithDetailsDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bookings")
public interface BookingController {
    @PostMapping()
    ResponseEntity<BookingDto> add(@Valid @RequestBody BookingRequestDto bookingRequestDto);

    @PutMapping("/{id}")
    ResponseEntity<Void> cancel(@PathVariable Long id);

    @PutMapping("/{bookingId}/{roomId}")
    ResponseEntity<BookingDto> updateRoom(@PathVariable Long bookingId, @PathVariable Long roomId);

    @GetMapping("/{userId}")
    ResponseEntity<List<BookingWithDetailsDto>> getAllByUserId(@PathVariable Long userId);
}
