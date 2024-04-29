package com.hotelmanagement.hotelmanagement.payload;

import lombok.Data;

@Data
public class AddingRoleRequest {
    public String username;
    public String userNameOfRoleTobeAssigned;
}
