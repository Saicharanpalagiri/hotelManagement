package com.hotelmanagement.hotelmanagement.Exceptions;

public class BookingFullException extends RuntimeException{
    public BookingFullException(String message) {
        super(message);
    }
}
