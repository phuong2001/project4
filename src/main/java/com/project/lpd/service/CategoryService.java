package com.project.lpd.service;

import com.project.lpd.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    List<CategoryEntity> getAllCategory();
    CategoryEntity getCategoryById(int id);
}
