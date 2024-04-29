package com.hotelmanagement.hotelmanagement.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hotelmanagement.hotelmanagement.entities.User;
import com.hotelmanagement.hotelmanagement.payload.AddingRoleRequest;
import com.hotelmanagement.hotelmanagement.services.UserService;
@RestController
@RequestMapping("api/private")
public class RoleController {
    @Autowired
    private UserService userService;
    @PostMapping("addrole")
    public ResponseEntity<?> addRole(@RequestBody AddingRoleRequest addingRoleRequest){
      System.out.println(addingRoleRequest.getUsername()+" un ");
      User user=userService.getRoles(addingRoleRequest.getUsername(),addingRoleRequest.getUserNameOfRoleTobeAssigned());
      return new ResponseEntity(user,HttpStatus.OK);
    }    
}
