package com.project.lpd.repository;

import com.project.lpd.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByCategoryid(int id);
    List<CategoryEntity> findAll();
}
