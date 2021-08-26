package com.project.lpd.service;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.NewsEntity;
import com.project.lpd.entity.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAllCategory();
    CategoryEntity getCategoryById(int id);
    CategoryEntity createCategory(CategoryEntity p);
    void deleteCategory(int id);
    CategoryEntity updateCategory(CategoryEntity p);
    List<CategoryEntity> getCaterorybyFullName(String name);


}
