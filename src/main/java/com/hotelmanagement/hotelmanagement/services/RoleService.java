package com.hotelmanagement.hotelmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagement.hotelmanagement.entities.Role;
import com.hotelmanagement.hotelmanagement.repositories.RoleRepository;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role getRoleByName(String roleName){
        return roleRepository.findByName(roleName).get();
    }    
}
