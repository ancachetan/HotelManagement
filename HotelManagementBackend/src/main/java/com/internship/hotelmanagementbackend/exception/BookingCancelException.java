package com.internship.hotelmanagementbackend.exception;

public class BookingCancelException extends RuntimeException {
    public BookingCancelException(String message) {
        super(message);
    }
}
