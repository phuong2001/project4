package com.project.lpd.repository;

import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
    @Query("select b from ProductEntity b where b.name like %:name%")
    List<ProductEntity> findByProductName(String name);

}
