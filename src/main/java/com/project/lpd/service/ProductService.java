package com.project.lpd.service;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.ProductDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
     List<ProductEntity> AllProduct(Pageable pageable);
     List<ProductEntity> getAllProduct();
     ProductEntity createProduct(ProductEntity productEntity);
     void updateProduct(ProductEntity p);
     ProductEntity getProductById(int id);
     void deleteProduct(int id);
     int getTotalPage(Pageable pageable);
     List<ProductEntity> getProductByUser(UserEntity userEntity);
     List<ProductEntity> getProductByCategory(CategoryEntity categoryEntity);
     List<ProductEntity> getProductByFullName(String name);
     int CountProduct(int id);
     List<ProductEntity> getTopByDate();
     List<ProductEntity> getTopPrice();
}
