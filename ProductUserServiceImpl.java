package com.project.lpd.service;

import com.project.lpd.entity.ProductUserEntity;
import com.project.lpd.repository.ProductUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUserServiceImpl implements ProductUserService {
    @Autowired
    ProductUserRepo productUserRepo;

    @Override
    public List<ProductUserEntity> getAllProductUser(Pageable pageable) {
        return productUserRepo.findAll(pageable).getContent();
    }


    @Override
    public int getTotalPage(Pageable pageable) {
        return productUserRepo.findAll(pageable).getTotalPages();
    }

    @Override
    public ProductUserEntity getUserById(int id) {
        return productUserRepo.findById(id).get();
    }

    @Override
    public ProductUserEntity createProductUser(ProductUserEntity p) {
        return productUserRepo.save(p);
    }

    @Override
    public void deleteProductUser(int id) {
        productUserRepo.deleteById(id);
    }

    @Override
    public ProductUserEntity updateProductUser(ProductUserEntity p) {
        return productUserRepo.save(p);
    }

    @Override
    public List<ProductUserEntity> getCategoryByFullName(String name) {
        return productUserRepo.findByFullName(name);
    }

}