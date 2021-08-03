package com.project.lpd.repository;

import com.project.lpd.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity getByCategoryid(int id);
}
