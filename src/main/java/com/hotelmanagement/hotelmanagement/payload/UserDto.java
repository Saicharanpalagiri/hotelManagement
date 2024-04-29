package com.hotelmanagement.hotelmanagement.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDto {
    public String name;
    public String email;
    public String password;
    @JsonIgnore
    public String mobileNumber;
    public UserDto(String name, String email, String password, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
    public UserDto() {
    }
}
