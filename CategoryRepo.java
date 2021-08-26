package com.project.lpd.repository;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ShipperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByCategoryid(int id);
    List<CategoryEntity> findAll();
    @Query("select n from CategoryEntity n  where n.name like  %:name%")
    List<CategoryEntity> findByFullName(String name);
}
