package com.hotelmanagement.hotelmanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import java.util.Set;
import java.util.HashSet;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    @Column(unique = true)
    private String mobileNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet();

    public User() {
    }

    public User(String name, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
}
