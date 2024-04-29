package com.hotelmanagement.hotelmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagement.hotelmanagement.entities.Hotel;
import com.hotelmanagement.hotelmanagement.payload.HotelDto;
import com.hotelmanagement.hotelmanagement.payload.HotelMapper;
import com.hotelmanagement.hotelmanagement.repositories.HotelRepository;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }


    public HotelDto createHotel(HotelDto hotelDto) {
        Hotel hotel=HotelMapper.mapToHotel(hotelDto);
        Hotel createdHotel= hotelRepository.save(hotel);
        return HotelMapper.mapToHotelDto(createdHotel);
    }

    public Hotel getHotelById(Long hotelId){
        return hotelRepository.findById(hotelId)
        .orElseThrow(()->new RuntimeException("Hotel not Found for the given Id "+hotelId));
    }


    public HotelDto updateAHotel(Hotel hotel) {
        // TODO Auto-generated method stub
        return HotelMapper.mapToHotelDto(hotelRepository.save(hotel));
    }
}
