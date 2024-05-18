package com.internship.hotelmanagementbackend.service.impl;

import com.internship.hotelmanagementbackend.dto.RoomDto;
import com.internship.hotelmanagementbackend.model.Booking;
import com.internship.hotelmanagementbackend.model.Room;
import com.internship.hotelmanagementbackend.repository.BookingRepository;
import com.internship.hotelmanagementbackend.repository.RoomRepository;
import com.internship.hotelmanagementbackend.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public RoomServiceImpl(RoomRepository roomRepository, BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoomDto> getAllAvailableInSpecifiedInterval(Long hotelId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> rooms = roomRepository.findByHotelId(hotelId);
        List<Room> availableRooms = new ArrayList<>();

        for (Room room : rooms) {
            boolean isAvailable = true;
            List<Booking> bookings = bookingRepository.findAllByRoomIdAndIsCanceledFalse(room.getId());
            for (Booking booking : bookings) {
                if (!(checkInDate.compareTo(booking.getCheckOutDate()) >=0 || checkOutDate.compareTo(booking.getCheckInDate()) <= 0)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                availableRooms.add(room);
            }
        }
        return availableRooms
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }
}
