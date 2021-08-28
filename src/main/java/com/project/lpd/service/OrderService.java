package com.project.lpd.service;

import com.project.lpd.entity.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderEntity p);
    OrderEntity saveOrder(OrderEntity p);
    List<OrderEntity> getAllOrder(Pageable pageable);
    int getTotalPage(Pageable pageable);
    List<OrderEntity> getOrderByUser(UserEntity userEntity);
//    List<OrderEntity> getOrderSeller(UserEntity user);
    OrderEntity getById(int id);

}
