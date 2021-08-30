package com.project.lpd.repository;

import com.project.lpd.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity , Integer> {
    List<OrderEntity> findByUser(UserEntity userEntity);
    List<OrderEntity> findByOrderItems(OrderItem orderItem);

    @Query(value = "select * from order_detail order by view desc limit :top", nativeQuery = true)
    List<OrderEntity> findTopOrder(int top);
}
