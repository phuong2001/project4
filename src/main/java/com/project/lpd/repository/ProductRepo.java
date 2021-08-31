package com.project.lpd.repository;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findById(int Id);
    List<ProductEntity> findByUser(UserEntity userEntity);
    List<ProductEntity> findByCategory(CategoryEntity categoryEntity);

    @Query("select b from ProductEntity b where "
            + "concat(b.name ,b.category.name ,b.price , b.description)"
            + "like %?1%" )
    List<ProductEntity> findByFullName(String name);

}
