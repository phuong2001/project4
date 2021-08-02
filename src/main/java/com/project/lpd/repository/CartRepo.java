package com.project.lpd.repository;

import com.project.lpd.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<CartEntity,Integer> {
    CartEntity findById(int id);
}
