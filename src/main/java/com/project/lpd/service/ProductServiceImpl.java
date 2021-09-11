package com.project.lpd.service;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.RoleEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.ProductDto;
import com.project.lpd.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<ProductEntity> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public int getTotalPage(Pageable pageable) {
        return productRepo.findAll(pageable).getTotalPages();
    }

    @Override
     public List<ProductEntity> getProductByUser(UserEntity userEntity) {
        return productRepo.findByUser(userEntity);
    }


    @Override
    public ProductEntity createProduct(ProductEntity productEntity){
        //ProductEntity product = new ProductEntity(productEntity.getName(),productEntity.getPrice(),productEntity.getQuantity(),productEntity.getDescription(),file.getBytes().toString());

        return productRepo.save(productEntity);
    }


    @Override
    public void updateProduct(ProductEntity p) {
         productRepo.save(p);
    }

    @Override
    public ProductEntity getProductById(int id) {
        return productRepo.getById(id);
    }

    @Override
    public void deleteProduct(int id) { productRepo.deleteById(id); }

    @Override
    public List<ProductEntity> getProductByCategory(CategoryEntity categoryEntity) {
        return productRepo.findByCategory(categoryEntity);
    }

    @Override
    public int CountProduct(int id) {
        return productRepo.CountById(id);
    }

    @Override
    public List<ProductEntity> getTopByDate() {
        return productRepo.findFirst15ByOrderByCreatedAtDesc();
    }

    @Override
    public List<ProductEntity> getTopPrice() {
        return productRepo.findFirst6ByOrderByPriceAsc();
    }

    @Override
    public List<ProductEntity> getConsoleProduct(CategoryEntity categoryEntity) {
        return productRepo.findFirst15OrderByCategory(categoryEntity);
    }


    @Override
    public List<ProductEntity> getProductByFullName(String name) {
        return productRepo.findByFullName(name);
    }

    @Override
    public List<ProductEntity> getRandom() {
        return productRepo.findRandamProduct();
    }


}


