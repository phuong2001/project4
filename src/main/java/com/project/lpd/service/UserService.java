package com.project.lpd.service;

import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    UserEntity signUpUser(UserDto userDto);
    List<UserEntity> getAllUser(Pageable pageable);
    int getTotalPage(Pageable pageable);
    UserEntity getUserById(int id);
    UserEntity getUserByName(String username);
    void deleteUser(int id);
    UserEntity updateUser(UserEntity p);
    UserEntity updateUserProfile(UserEntity userEntity);
    List<UserEntity> getUserByFullName(String name);
    int quantityUser();
}
