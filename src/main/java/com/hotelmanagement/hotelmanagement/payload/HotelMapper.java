package com.hotelmanagement.hotelmanagement.payload;

import com.hotelmanagement.hotelmanagement.entities.Hotel;

public class HotelMapper {
    

    public static Hotel mapToHotel(HotelDto hotelDto){
        Hotel hotel=new Hotel(hotelDto.getName(), hotelDto.getAddress(), 
        hotelDto.getAddress(), hotelDto.getDescription(), hotelDto.getNoOfRooms());
        return hotel;
    }

    public static HotelDto mapToHotelDto(Hotel hotel){
        HotelDto hotelDto=new HotelDto(hotel.getName(), hotel.getAddress(), 
        hotel.getAddress(), hotel.getDescription(), hotel.getNoOfRooms());
        return hotelDto;
    }
}
