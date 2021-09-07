package com.project.lpd.repository;

import com.project.lpd.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity , Integer> {
    List<OrderEntity> findByUser(UserEntity userEntity);
    List<OrderEntity> findByOrderItems(OrderItem orderItem);

    @Query("select b from OrderEntity b where "
            + "concat(b.fullname ,b.address ,b.phone , b.user.fullName)"
            + "like %?1%")
     List<OrderEntity> findByFullName(String fullname);
}
