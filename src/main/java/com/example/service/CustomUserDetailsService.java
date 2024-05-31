package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("not this fun impl");
    }

    public org.springframework.security.core.userdetails.User loadUserById(Long id, String phoneNumber) throws UsernameNotFoundException {
        User user = userMapper.selectById(id);
        if (user == null || !phoneNumber.equals(user.getPhoneNumber())) {
            throw new UsernameNotFoundException("not this user" + phoneNumber);
        }
        return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), user.getPassword(), new ArrayList<>());
    }

}



