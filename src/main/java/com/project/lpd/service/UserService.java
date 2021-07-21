package com.project.lpd.service;

import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    UserEntity signUpUser(UserDto userDto);
}
