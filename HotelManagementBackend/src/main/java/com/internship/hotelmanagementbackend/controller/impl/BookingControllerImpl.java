package com.internship.hotelmanagementbackend.controller.impl;

import com.internship.hotelmanagementbackend.controller.BookingController;
import com.internship.hotelmanagementbackend.dto.BookingDto;
import com.internship.hotelmanagementbackend.dto.BookingRequestDto;
import com.internship.hotelmanagementbackend.dto.BookingWithDetailsDto;
import com.internship.hotelmanagementbackend.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookingControllerImpl implements BookingController {
    private final BookingService bookingService;

    public BookingControllerImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public ResponseEntity<BookingDto> add(BookingRequestDto bookingRequestDto) {
        return new ResponseEntity<>(bookingService.add(bookingRequestDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancel(Long id) {
        bookingService.cancel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingDto> updateRoom(Long bookingId, Long roomId) {
        return new ResponseEntity<>(bookingService.updateRoom(bookingId, roomId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookingWithDetailsDto>> getAllByUserId(Long userId) {
        return new ResponseEntity<>(bookingService.getAllByUserId(userId), HttpStatus.OK);
    }
}
