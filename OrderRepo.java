package com.project.lpd.repository;

import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity , Integer> {
    List<OrderEntity> findByUser(UserEntity userEntity);

}
