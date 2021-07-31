package com.project.lpd.service;

import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.RoleEntity;
import com.project.lpd.model.ProductDto;
import com.project.lpd.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public int getTotalPage(Pageable pageable) {
        return productRepo.findAll(pageable).getTotalPages();
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
}
