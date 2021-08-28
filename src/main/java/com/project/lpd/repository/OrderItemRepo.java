package com.project.lpd.repository;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.OrderItem;
import com.project.lpd.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByProducts(ProductEntity productEntity);
    List<OrderItem> findByOrders(OrderEntity orderEntity);
}
