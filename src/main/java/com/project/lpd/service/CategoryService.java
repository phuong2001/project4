package com.project.lpd.service;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryEntity> getAllCategory();
    CategoryEntity getCategoryById(int id);
    CategoryEntity createCategory(CategoryEntity p);
    void deleteCategory(int id);
    CategoryEntity updateCategory(CategoryEntity p);
    CategoryEntity getCategoryByName(String name);
    List<CategoryEntity> getCategoryByFullName(String name);
    int getTotalPage(Pageable pageable);
    CategoryEntity getFullCategorys(String name);
    List<CategoryEntity> getAll(Pageable pageable);

}
