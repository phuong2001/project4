package com.project.lpd.repository;

import com.project.lpd.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity , Integer> {

}
