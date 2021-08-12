package com.project.lpd.service;

import com.project.lpd.entity.NewsEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.repository.ProductRepo;
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
    public List<ProductEntity> AllProduct(Pageable pageable) {
            return productUserRepo.findAll(pageable).getContent();
    }

    @Override
    public int getTotalPage(Pageable pageable) {
        return productUserRepo.findAll(pageable).getTotalPages();
    }


    @Override
    public ProductEntity createProduct(ProductEntity productEntity){
        return productUserRepo.save(productEntity);
    }

    @Override
    public void updateProduct(ProductEntity p) {
         productUserRepo.save(p);
    }

    @Override
    public ProductEntity getProductById(int id) {
        return productUserRepo.findById(id).get();
    }

    @Override
    public void deleteProduct(int id) { productUserRepo.deleteById(id); }
}
