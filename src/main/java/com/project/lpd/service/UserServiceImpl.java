package com.project.lpd.service;


import com.project.lpd.entity.UserEntity;
import com.project.lpd.exception.UserAlreadyExistException;
import com.project.lpd.model.UserDto;
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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder;

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

    /*@Override
    public UserEntity createUser(UserEntity p) {
        return userRepo.save(p);
    }*/

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);

    }

    @Override
    public UserEntity updateUser(UserEntity p) {
        return userRepo.save(p);
    }

    @Override
    public List<UserEntity> listAll(String keyword) {
        if (keyword != null){
            return userRepo.search(keyword);
        }
        return  userRepo.findAll();
    }






    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Not found username");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRole().getName()));
        return new User(username, userEntity.getPassword(), authorities);
    }

    @Override
    public UserEntity signUpUser(UserDto userDto){
        if(emailExists(userDto.getEmail())){
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }
        final UserEntity user = new UserEntity();
        user.setFullName(userDto.getFullName());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        return userRepo.save(user);
    }


    private boolean emailExists(final String email) {
        return userRepo.findByEmail(email) != null;
    }

}
