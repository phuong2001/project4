package com.project.lpd.repository;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.OrderItem;
import com.project.lpd.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByProducts(ProductEntity productEntity);
    List<OrderItem> findByOrders(OrderEntity orderEntity);
    @Query(value = "SELECT *, SUM(orderitem.quantity) AS total FROM orderitem GROUP BY productid ORDER BY SUM(orderitem.quantity) DESC LIMIT 10", nativeQuery = true)
    List<OrderItem> findTopOrder();



}
