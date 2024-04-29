package com.hotelmanagement.hotelmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String location;
    private String description;
    private Integer noOfRooms;
    public Hotel(String name, String address, String location, String description, Integer noOfRooms) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.description = description;
        this.noOfRooms = noOfRooms;
    }

    public Hotel(){}
}
