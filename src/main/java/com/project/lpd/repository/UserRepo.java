package com.project.lpd.repository;


import com.project.lpd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
        UserEntity findByUsername(String username);
        UserEntity findByEmail(String email);
}
