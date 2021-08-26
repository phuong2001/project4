package com.project.lpd.repository;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.ShipperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShippeRepo extends JpaRepository<ShipperEntity,Integer> {
    @Query("select n from ShipperEntity n  where n.name like  %:name%")
    List<ShipperEntity> findByFullName(String name);


}