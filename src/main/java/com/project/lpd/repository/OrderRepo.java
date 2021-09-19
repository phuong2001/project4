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
     List<OrderEntity> findByStatus(String status);

    @Query(value = "SELECT COUNT(status) AS done FROM order_detail  WHERE status='DONE' AND order_detail.userid=:id", nativeQuery = true)
    int countAllDone(int id);


    @Query(value = "SELECT COUNT(status) AS paid FROM order_detail WHERE status='PAID' AND order_detail.userid=:id", nativeQuery = true)
    int countAllPaid(int id);

    @Query(value = "SELECT SUM(order_detail.price_total) FROM order_detail WHERE order_detail.status='DONE' ", nativeQuery = true)
    double TotalDone();

    @Query(value = "SELECT * FROM order_detail INNER JOIN product INNER JOIN orderitem" +
            " INNER JOIN user ON order_detail.orderid=orderitem.orderid AND product.productid = orderitem.productid AND user.id=product.userid " +
            "WHERE order_detail.status='DONE' AND user.id=:id", nativeQuery = true)
    List<OrderEntity> listOrderDoneUser(int id);
}
