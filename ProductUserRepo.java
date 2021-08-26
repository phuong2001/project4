package com.project.lpd.repository;

import com.project.lpd.entity.ProductUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductUserRepo extends JpaRepository<ProductUserEntity, Integer> {
    @Query("select b from ProductUserEntity b where b.name like %:name%")
    List<ProductUserEntity> findByFullName(String name);
}


