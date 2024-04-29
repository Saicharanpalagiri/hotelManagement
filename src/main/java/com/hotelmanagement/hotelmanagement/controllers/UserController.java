package com.hotelmanagement.hotelmanagement.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagement.hotelmanagement.payload.JwtResponse;
import com.hotelmanagement.hotelmanagement.payload.LoginRequest;
import com.hotelmanagement.hotelmanagement.payload.UserDto;
import com.hotelmanagement.hotelmanagement.services.UserService;
import com.hotelmanagement.hotelmanagement.utils.JwtUtils;
import com.hotelmanagement.hotelmanagement.utils.UserDetailsImpl;

@RestController
@RequestMapping("api/public")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("signup")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        if (userService.existsByEmail(userDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }
    
    @PostMapping("signin")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
            List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
                return ResponseEntity.ok(new JwtResponse(jwt, 
                                 userDetails.getId(), 
                                 userDetails.getUsername(), 
                                 userDetails.getEmail(), 
                                 roles));
    }
}