package com.project.lpd.repository;

import com.project.lpd.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity , Integer> {
    List<OrderEntity> findByUser(UserEntity userEntity);
    List<OrderEntity> findByOrderItems(OrderItem orderItem);
}
