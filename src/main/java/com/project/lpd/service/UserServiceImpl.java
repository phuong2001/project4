package com.project.lpd.service;


import com.project.lpd.entity.RoleEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.exception.UserAlreadyExistException;
import com.project.lpd.model.UserDto;
import com.project.lpd.repository.RoleRepo;
import com.project.lpd.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAllUser(Pageable pageable) {
        return userRepo.findAll(pageable).getContent() ;
    }

    @Override
    public int getTotalPage(Pageable pageable) {
        return userRepo.findAll(pageable).getTotalPages();
    }

    @Override
    public UserEntity getUserById(int id) {
        return userRepo.findById( id).get();
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);

    }

    @Override
    public UserEntity updateUser(UserEntity p) {
        return userRepo.save(p);
    }

    @Override
    public List<UserEntity> getUserByFullName(String name) {
        return userRepo.findByFullName(name);

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Not found username");
        }

        return new User(username, userEntity.getPassword(), mapRolesToAuthorities(userEntity.getRoles()));
    }

    @Override
    public UserEntity signUpUser(UserDto userDto){
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        } else if (usernameExists(userDto.getUsername())){
            throw new UserAlreadyExistException("Username already taken " + userDto.getUsername());
        } else {
            UserEntity userEntity = new UserEntity(userDto.getUsername(), userDto.getFullName(), userDto.getEmail(),
                    passwordEncoder.encode(userDto.getPassword()), Arrays.asList(roleRepo.findByName("USER")));
            return userRepo.save(userEntity);
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    private boolean emailExists(final String email) {
        return userRepo.findByEmail(email) != null;
    }
    private boolean usernameExists(final String username){return userRepo.findByUsername(username) != null;}

}
