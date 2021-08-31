package com.project.lpd.repository;

import com.project.lpd.entity.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByCategoryid(int id);
    List<CategoryEntity> findAll();
     CategoryEntity findByName(String name);
    @Query("select b from CategoryEntity b where b.name like %:name%")
     List<CategoryEntity> findByFullName(String name);

}
