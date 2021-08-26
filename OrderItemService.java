package com.project.lpd.service;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.OrderItem;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;

public interface OrderItemService {
    OrderItem saveItem(OrderItem orderItem);
    void saveOrderItem( UserEntity userEntity , OrderEntity orderEntity);
}
