package com.project.lpd.service;

import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
     List<ProductEntity> AllProduct(Pageable pageable);
     ProductEntity createProduct(ProductEntity productEntity);
     void updateProduct(ProductEntity p);
     ProductEntity getProductById(int id);
     void deleteProduct(int id);
     int getTotalPage(Pageable pageable);

}
