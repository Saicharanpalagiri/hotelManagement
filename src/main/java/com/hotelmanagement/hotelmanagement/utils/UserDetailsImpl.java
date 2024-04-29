package com.hotelmanagement.hotelmanagement.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hotelmanagement.hotelmanagement.entities.User;

import java.util.Objects;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails{
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id,String email, String password, Collection<? extends GrantedAuthority> authorities){
        this.id=id;
        this.email = email;
        this.password =password;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities=user.getRoles()
        .stream().map(role->new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
        return new UserDetailsImpl(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            authorities
        );
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return authorities;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
      return password;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
       return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
        }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      UserDetailsImpl user = (UserDetailsImpl) o;
      return Objects.equals(id,user.id);
    } 
}
