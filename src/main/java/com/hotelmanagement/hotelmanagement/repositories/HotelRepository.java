package com.hotelmanagement.hotelmanagement.repositories;

import org.springframework.stereotype.Repository;

import com.hotelmanagement.hotelmanagement.entities.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long>{
}
