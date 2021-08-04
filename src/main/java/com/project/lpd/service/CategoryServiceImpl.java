package com.project.lpd.service;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<CategoryEntity> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(int id) {
        return categoryRepo.getById(id);
    }
}
