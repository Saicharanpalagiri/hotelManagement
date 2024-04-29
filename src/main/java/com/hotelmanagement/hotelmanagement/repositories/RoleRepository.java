package com.hotelmanagement.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.hotelmanagement.hotelmanagement.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    Optional<Role> findByName(String roleName);
}
