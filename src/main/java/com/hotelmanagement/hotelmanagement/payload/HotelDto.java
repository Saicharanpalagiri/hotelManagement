package com.hotelmanagement.hotelmanagement.payload;

import lombok.Data;

@Data
public class HotelDto {
    private String username;
    private String name;
    private String address;
    private String location;
    private String description;
    private Integer noOfRooms;
    public HotelDto(String username,String name, String address, String location, String description, Integer noOfRooms) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.description = description;
        this.noOfRooms = noOfRooms;
        this.username = username;
    }
    public HotelDto(String name, String address, String location, String description, Integer noOfRooms) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.description = description;
        this.noOfRooms = noOfRooms;
    }  
    public HotelDto() {
    }  
}
