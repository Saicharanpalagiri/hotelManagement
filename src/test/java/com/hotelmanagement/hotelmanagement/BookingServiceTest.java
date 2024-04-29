package com.hotelmanagement.hotelmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotelmanagement.hotelmanagement.entities.Booking;
import com.hotelmanagement.hotelmanagement.entities.Hotel;
import com.hotelmanagement.hotelmanagement.repositories.BookingRepository;
import com.hotelmanagement.hotelmanagement.services.BookingService;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;
    @InjectMocks
    private BookingService bookingService;


    @Test
    public void testMakeBooking(){
        Booking booking=new Booking();
        booking.setHotel(new Hotel());
        booking.getHotel().setNoOfRooms(5);
        when(bookingRepository.findByHotel(booking.getHotel())).thenReturn(new ArrayList<>());
        Booking savedBooking = new Booking();
        savedBooking.setId(1L);
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);
        Object result = bookingService.makeABooking(booking);
        assertEquals(savedBooking, result);
    }

     @Test
    public void testCancelABooking() {
        Long bookingId = 1L;
        bookingService.cancelABooking(bookingId);
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
}
