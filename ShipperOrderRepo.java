package com.project.lpd.repository;

import com.project.lpd.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipperOrderRepo extends JpaRepository<OrderEntity ,Integer> {
    @Query("select p from ShipperEntity  p where p.name like  %:name%")
    List<OrderEntity> findAllByName(String name);
}
