package com.hotelmanagement.hotelmanagement.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagement.hotelmanagement.entities.Booking;
import com.hotelmanagement.hotelmanagement.entities.Hotel;
import com.hotelmanagement.hotelmanagement.entities.Role;
import com.hotelmanagement.hotelmanagement.entities.User;
import com.hotelmanagement.hotelmanagement.payload.BookingRequest;
import com.hotelmanagement.hotelmanagement.payload.HotelDto;
import com.hotelmanagement.hotelmanagement.services.BookingService;
import com.hotelmanagement.hotelmanagement.services.HotelService;
import com.hotelmanagement.hotelmanagement.services.RoleService;
import com.hotelmanagement.hotelmanagement.services.UserService;

@RestController
@RequestMapping("/api/private/hotels")
public class HotelController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody HotelDto hotelDto) {
        System.out.println("entered inside the hotel creation");
        System.out.println(hotelDto.getLocation() + " " + hotelDto.getUsername() + " hotelDto name ");
        Set<Role> roles = userService.getByEmail(hotelDto.getUsername()).getRoles();
        Role role = roleService.getRoleByName("ADMIN");
        if (!roles.contains(role)) {
            return ResponseEntity.badRequest().build();
        }
        HotelDto hotel = hotelService.createHotel(hotelDto);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }

    @PostMapping("{hotelId}/book")
    public ResponseEntity<?> makeABooking(@PathVariable("hotelId") Long hotelId,@RequestBody BookingRequest bookingRequest) {
        // User user=userService.getByEmail()
        User user=userService.getByEmail(bookingRequest.getUsername());
        Hotel hotel=hotelService.getHotelById(hotelId);
        // System.out.println(user.getName()+" user hotel "+hotel.getId());
        Booking booking=new Booking();
        booking.setUser(user);
        booking.setHotel(hotel);
        booking.setBookingDays(bookingRequest.getBookingDays());
        booking.setCheckinDate(bookingRequest.getCheckinDate());
        Object BookingObj=bookingService.makeABooking(booking);
        return new ResponseEntity<>(BookingObj, HttpStatus.CREATED);
    }


    @PutMapping("{hotelId}")
    public ResponseEntity<?> updateAHotel(@PathVariable("hotelId") long id, @RequestBody HotelDto hotelDto){
        Set<Role> roles=userService.getByEmail(hotelDto.getUsername()).getRoles();
        Role role=roleService.getRoleByName("HOTEL_MANAGER");
        if(!roles.contains(role)){
            return new ResponseEntity<>("Only Hotel Managers are allowed to update a Hotel",HttpStatus.BAD_REQUEST);
        }       
        Hotel hotel=hotelService.getHotelById(id);
        hotel.setName(hotelDto.getName());
        hotel.setAddress(hotelDto.getAddress());
        hotel.setLocation(hotelDto.getLocation());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setNoOfRooms(hotelDto.getNoOfRooms());
        HotelDto updatedHotel=hotelService.updateAHotel(hotel);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }
}
