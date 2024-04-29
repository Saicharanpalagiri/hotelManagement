package com.hotelmanagement.hotelmanagement.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingRequest {
    private String username;
    private LocalDateTime checkinDate;
    private int bookingDays; 
    // private Long hotelId;
}
