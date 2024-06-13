package com.bookstore.services;

import com.bookstore.entity.CustomUserDetail;
import com.bookstore.entity.User;
import com.bookstore.repository.IUerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUerRepository  uerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = uerRepository.findByUsername(username);
        if (user == null){
            throw  new UsernameNotFoundException(username);
        }
        return  new CustomUserDetail(user,uerRepository);
    }
}
