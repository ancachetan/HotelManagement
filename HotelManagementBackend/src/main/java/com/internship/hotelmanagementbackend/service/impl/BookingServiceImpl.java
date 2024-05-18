package com.internship.hotelmanagementbackend.service.impl;

import com.internship.hotelmanagementbackend.dto.BookingDto;
import com.internship.hotelmanagementbackend.dto.BookingRequestDto;
import com.internship.hotelmanagementbackend.dto.BookingWithDetailsDto;
import com.internship.hotelmanagementbackend.exception.BookingUpdateException;
import com.internship.hotelmanagementbackend.exception.ResourceNotFoundException;
import com.internship.hotelmanagementbackend.model.Booking;
import com.internship.hotelmanagementbackend.model.Hotel;
import com.internship.hotelmanagementbackend.model.Room;
import com.internship.hotelmanagementbackend.model.User;
import com.internship.hotelmanagementbackend.repository.BookingRepository;
import com.internship.hotelmanagementbackend.repository.HotelRepository;
import com.internship.hotelmanagementbackend.repository.RoomRepository;
import com.internship.hotelmanagementbackend.repository.UserRepository;
import com.internship.hotelmanagementbackend.service.BookingService;
import com.internship.hotelmanagementbackend.util.ExceptionMessages;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository, UserRepository userRepository, HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BookingDto add(BookingRequestDto bookingRequestDto) {
        Booking booking = modelMapper.map(bookingRequestDto, Booking.class);
        User user = userRepository.findById(bookingRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.USER_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(bookingRequestDto.getUserId())));
        Room room = roomRepository.findById(bookingRequestDto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.ROOM_NOT_FOUND_EXCEPTION_MESSAGE.formatted(bookingRequestDto.getRoomId())));

        booking.setUser(user);
        booking.setRoom(room);
        booking.setCanceled(false);
        Booking savedBooking = bookingRepository.save(booking);
        user.getBookings().add(savedBooking);
        room.getBookings().add(savedBooking);

        return modelMapper.map(savedBooking, BookingDto.class);
    }

    @Override
    public void cancel(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.BOOKING_NOT_FOUND_EXCEPTION_MESSAGE.formatted(id)));
        if (isModificationNotPossible(booking.getCheckInDate(), booking.getRoom().getHotel().getCheckInTime())) {
            throw new BookingUpdateException(ExceptionMessages.BOOKING_CANCEL_EXCEPTION_MESSAGE);
        }
        booking.setCanceled(true);
        bookingRepository.save(booking);
    }

    @Override
    public BookingDto updateRoom(Long bookingId, Long roomId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.BOOKING_NOT_FOUND_EXCEPTION_MESSAGE.formatted(bookingId)));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.ROOM_NOT_FOUND_EXCEPTION_MESSAGE.formatted(roomId)));
        Hotel hotel = hotelRepository.findById(room.getHotel().getId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.HOTEL_NOT_FOUND_EXCEPTION_MESSAGE.formatted(room.getHotel().getId())));
        if (isModificationNotPossible(booking.getCheckInDate(), hotel.getCheckInTime())) {
            throw new BookingUpdateException(ExceptionMessages.BOOKING_ROOM_UPDATE_EXCEPTION_MESSAGE);
        }
        booking.setRoom(room);
        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDto.class);
    }

    @Override
    public List<BookingWithDetailsDto> getAllByUserId(Long userId) {
        return this.bookingRepository.findAllByUserId(userId)
                .stream()
                .map(booking -> modelMapper.map(booking, BookingWithDetailsDto.class))
                .collect(Collectors.toList());
    }

    private boolean isModificationNotPossible(LocalDate bookingCheckInDate, LocalTime hotelCheckInTime) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalTime twoHoursBeforeCheckIn = hotelCheckInTime.minusHours(2);
        return bookingCheckInDate.equals(date) && time.isAfter(twoHoursBeforeCheckIn) || bookingCheckInDate.isBefore(date);
    }
}
