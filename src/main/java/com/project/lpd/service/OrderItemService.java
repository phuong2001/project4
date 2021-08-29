package com.project.lpd.service;

import com.project.lpd.entity.*;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem saveItem(OrderItem orderItem);
    void saveOrderItem( UserEntity userEntity , OrderEntity orderEntity);
    List<OrderItem> findByProduct(ProductEntity productEntity);
    List<OrderItem> getAllOr();
    List<OrderItem> findByOrders(OrderEntity orderEntity);
}
