package com.hotelmanagement.hotelmanagement.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanagement.hotelmanagement.entities.Role;
import com.hotelmanagement.hotelmanagement.entities.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    //  Optional<User>  findByUsername(String username);
     Optional<User> findByEmail(String email);
     Optional<User> findByName(String userName);
}