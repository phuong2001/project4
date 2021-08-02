package com.project.lpd.repository;

import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<CartItemEntity,Integer> {
    CartItemEntity findById(int id);
    List<CartItemEntity> findByUser(UserEntity userEntity);
}
