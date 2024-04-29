package com.hotelmanagement.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanagement.hotelmanagement.entities.Booking;
import com.hotelmanagement.hotelmanagement.entities.Hotel;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{
    List<Booking> findByHotel(Hotel hotel);
}
