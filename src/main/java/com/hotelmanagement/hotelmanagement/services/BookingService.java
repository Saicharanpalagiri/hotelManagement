package com.hotelmanagement.hotelmanagement.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotelmanagement.hotelmanagement.Exceptions.BookingFullException;
import com.hotelmanagement.hotelmanagement.entities.Booking;
import com.hotelmanagement.hotelmanagement.repositories.BookingRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Object makeABooking(Booking booking) {
        Integer totalNoOfRooms=booking.getHotel().getNoOfRooms();
        List<Booking> bookings=bookingRepository.findByHotel(booking.getHotel());
        List<Booking> bkgs=bookings.stream().
        filter(bkg->bkg.getCheckoutDate().isBefore(LocalDateTime.now()))
        .collect(Collectors.toList());        
        if(totalNoOfRooms==bookings.size()-bkgs.size()){
           return new BookingFullException("All Rooms are booked for the hotel requested");
        }
        Booking savedBooking=bookingRepository.save(booking);
        return savedBooking;
    }

    public void cancelABooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
