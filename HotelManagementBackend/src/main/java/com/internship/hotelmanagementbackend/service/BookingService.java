package com.internship.hotelmanagementbackend.service;

import com.internship.hotelmanagementbackend.dto.BookingDto;
import com.internship.hotelmanagementbackend.dto.BookingRequestDto;
import com.internship.hotelmanagementbackend.dto.BookingWithDetailsDto;

import java.util.List;

public interface BookingService {
    BookingDto add(BookingRequestDto bookingRequestDto);

    void cancel(Long id);

    BookingDto updateRoom(Long bookingId, Long roomId);

    List<BookingWithDetailsDto> getAllByUserId(Long userId);
}
