package com.hotelmanagement.hotelmanagement.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotelmanagement.hotelmanagement.entities.Role;
import com.hotelmanagement.hotelmanagement.entities.User;
import com.hotelmanagement.hotelmanagement.payload.UserDto;
import com.hotelmanagement.hotelmanagement.payload.UserMapper;
import com.hotelmanagement.hotelmanagement.repositories.RoleRepository;
import com.hotelmanagement.hotelmanagement.repositories.UserRepository;
import com.hotelmanagement.hotelmanagement.utils.UserDetailsImpl;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserDto createUser(UserDto userDto){
        User user=UserMapper.maptoUser(userDto);
        // user.setRole(new Role(RoleTypes.java));
        // user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> roles=user.getRoles();
        Role role=roleRepository.findByName("CUSTOMER").get();
        roles.add(role);
        user.setRoles(roles);
        User savedUser=userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    public UserDetails loadUserByUsername(String username) {
        // TODO Auto-generated method stub
        // User user = userRepository.findByUsername(username)
        // .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        User user = userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    public User getByEmail(String email){
        User user=userRepository.findByEmail(email)
        .orElseThrow(()->new RuntimeException("User not found with these name"));
        System.out.println(user+" user ");
        return user;
    }

    public Boolean existsByEmail(String email){
        Optional<User> user=userRepository.findByEmail(email);
        if(!user.isEmpty()){
            return true;
        }
        return false;
    }

    public User getRoles(String userName,String userNameOfRoleTobeAssigned){
        User user=userRepository.findByEmail(userName).get();
        Set<Role> roles=user.getRoles();
        User userToBeRoleAssigned=userRepository.findByEmail(userNameOfRoleTobeAssigned).get();
        Boolean role=roles.stream().anyMatch(r->r.getName().equals("ADMIN"));
        Set<Role> rolesOfExistingUser=userToBeRoleAssigned.getRoles();
        Boolean roleOfExistingUser=rolesOfExistingUser.stream().anyMatch(r->r.getName().equals("ADMIN"));
        if(role && !roleOfExistingUser){
            Role r=roleRepository.findByName("ADMIN").get();
            rolesOfExistingUser.add(r);
        }else{
            System.out.println("role already assigned");
            return userToBeRoleAssigned;
        }
        userToBeRoleAssigned.setRoles(rolesOfExistingUser);
        userRepository.save(userToBeRoleAssigned);
        return userToBeRoleAssigned;
    }

    public Set<Role> getUserRoles(UserDto userDto){
        User user=userRepository.findByEmail(userDto.getEmail()).get();
        return user.getRoles();
    }
}
