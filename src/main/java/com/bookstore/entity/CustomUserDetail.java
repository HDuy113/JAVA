package com.bookstore.entity;

import com.bookstore.repository.IUerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetail implements UserDetails {
    private final User user;
    private final IUerRepository uerRepository;

    public  CustomUserDetail(User user, IUerRepository uerRepository){
        this.user = user;
        this.uerRepository = uerRepository;
    }



    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Arrays.stream(uerRepository.getRoleOfUser(user.getId()))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }


}
