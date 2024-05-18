package com.internship.hotelmanagementbackend.util;

public final class ExceptionMessages {
    public static final String USER_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE = "User not found with id: %s";
    public static final String USER_NOT_FOUND_BY_CREDENTIALS_MESSAGE = "User not found with username: %s";
    public static final String BOOKING_NOT_FOUND_EXCEPTION_MESSAGE = "Booking not found with id: %s";
    public static final String HOTEL_NOT_FOUND_EXCEPTION_MESSAGE = "Hotel not found with id: %s";
    public static final String ROOM_NOT_FOUND_EXCEPTION_MESSAGE = "Room not found with id: %s";
    public static final String BOOKING_ROOM_UPDATE_EXCEPTION_MESSAGE = "Booking update not possible";
    public static final String BOOKING_CANCEL_EXCEPTION_MESSAGE = "Booking cancel not possible";
    public static final String ALREADY_EXISTING_USER_EXCEPTION_MESSAGE = "User with username %s already exists";
    public static final String VALIDATION_EXCEPTION_MESSAGE = "Validation exception";


    private ExceptionMessages() {
    }
}
