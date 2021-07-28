package com.project.lpd.service;

import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.RoleEntity;
import com.project.lpd.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;


    @Override
    public List<ProductEntity> AllProduct(Pageable pageable) {
            return productRepo.findAll(pageable).getContent();
    }

    @Override
    public int getTotalPage(Pageable pageable) {
        return productRepo.findAll(pageable).getTotalPages();
    }


    @Override
    public ProductEntity createProduct(ProductEntity p) {
        return null;
    }

    @Override
    public ProductEntity updateProduct(ProductEntity p) {
        return null;
    }

    @Override
    public ProductEntity getProductById(int id) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }
}
