package com.project.lpd.service;

import com.project.lpd.entity.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderEntity p);
    OrderEntity saveOrder(OrderEntity p);
    List<OrderEntity> getAllOrder(Pageable pageable);
    List<OrderEntity> getAllOrd();
    int getTotalPage(Pageable pageable);
    List<OrderEntity> getOrderByUser(UserEntity userEntity);
    List<OrderEntity> findByOrderItems(OrderItem orderItem);
//    List<OrderEntity> getOrderSeller(UserEntity user);
    OrderEntity getById(int id);
    List<OrderEntity> getOrderByFullName(String fullname);
    List<OrderEntity> geyOrderByStatus(String status);
    int getCountOrderDone(int id);
    int getCountOrderPaid(int id);
    double TotalDone();
    double sumPriceUser(int id);
}
