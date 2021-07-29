package com.project.lpd.service;

import com.project.lpd.entity.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
     List<ProductEntity> AllProduct(Pageable pageable);
     ProductEntity createProduct(ProductEntity p);
     ProductEntity updateProduct(ProductEntity p);
     ProductEntity getProductById(int id);
     void deleteProduct(int id);
     int getTotalPage(Pageable pageable);

}
