package com.project.lpd.repository;

import com.project.lpd.entity.ShipperEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShippeRepo extends JpaRepository<ShipperEntity,Integer> {
    @Query("select p from ShipperEntity  p where p.name like  %:name%")
    List<ShipperEntity> findAllByName(String name);
    @Query("select p from  ShipperEntity  p where p.name like %:name%")
    Page<ShipperEntity> findAllByName(String name , Pageable pageable);
}