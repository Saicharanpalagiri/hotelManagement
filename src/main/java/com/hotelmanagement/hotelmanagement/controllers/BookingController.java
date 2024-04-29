package com.hotelmanagement.hotelmanagement.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagement.hotelmanagement.entities.Role;
import com.hotelmanagement.hotelmanagement.payload.UserRequest;
import com.hotelmanagement.hotelmanagement.services.BookingService;
import com.hotelmanagement.hotelmanagement.services.RoleService;
import com.hotelmanagement.hotelmanagement.services.UserService;

@RestController
@RequestMapping("api/private/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @DeleteMapping("{bookingId}")
    public ResponseEntity<?> cancelABooking(@PathVariable("bookingId") Long bookingId,@RequestBody UserRequest userRequest){
        Set<Role> roles=userService.getByEmail(userRequest.getUsername()).getRoles();
        Role role=roleService.getRoleByName("HOTEL_MANAGER");
        if(!roles.contains(role)){
            return new ResponseEntity<>("Only Hotel Managers are allowed to cancel a booking",HttpStatus.BAD_REQUEST);
        }        
        bookingService.cancelABooking(bookingId);
        return new ResponseEntity<>("Booking cancelled successfully",HttpStatus.OK);
    }
}
