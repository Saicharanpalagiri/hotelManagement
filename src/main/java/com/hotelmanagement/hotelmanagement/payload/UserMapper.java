package com.hotelmanagement.hotelmanagement.payload;

import com.hotelmanagement.hotelmanagement.entities.User;

public class UserMapper {
    
    public static User maptoUser(UserDto userDto){
        User user=new User(userDto.getName(),userDto.getEmail(),userDto.getPassword(),
        userDto.getMobileNumber());
        return user;
    }

    public static UserDto mapToUserDto(User user){
        UserDto userDto=new UserDto(user.getName(),user.getEmail(),user.getPassword(),
        user.getMobileNumber());
        return userDto;
    }
}
