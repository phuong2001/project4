package com.project.lpd.repository;


import com.project.lpd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
        UserEntity findByUsername(String username);
}
