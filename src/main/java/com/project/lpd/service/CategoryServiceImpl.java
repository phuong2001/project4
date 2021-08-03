package com.project.lpd.service;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
        return categoryRepo.getByCategoryid(id);
    }

    @Override
    public CategoryEntity createCategory(CategoryEntity p) {
        return categoryRepo.save(p);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity p) {
        return categoryRepo.save(p);
    }
}
