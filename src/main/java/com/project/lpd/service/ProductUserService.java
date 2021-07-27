package com.project.lpd.service;

import com.project.lpd.entity.ProductUserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductUserService {
    List<ProductUserEntity> getAllProductUser(Pageable pageable);

    int getTotalPage(Pageable pageable);
    ProductUserEntity getUserById(int id);
    ProductUserEntity createProductUser(ProductUserEntity p);
    void deleteProductUser(int id);
    ProductUserEntity updateProductUser(ProductUserEntity p);

}
