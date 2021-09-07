package com.project.lpd.repository;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
    @Query("select b from ProductEntity b where b.name like %:name%")
    List<ProductEntity> findByProductName(String name);
    ProductEntity findById(int Id);
    List<ProductEntity> findByUser(UserEntity userEntity);
    List<ProductEntity> findByCategory(CategoryEntity categoryEntity);
    @Query("SELECT count (u) FROM ProductEntity u where u.userid = :id")
    int CountById(int id);

    @Query("select b from ProductEntity b where "
            + "concat(b.name ,b.category.name ,b.price , b.description)"
            + "like %?1%" )
    List<ProductEntity> findByFullName(String name);

    @Query("select  b from ProductEntity b order by b.createdAt DESC ")
    List<ProductEntity> findAllByCreatedAt(Pageable pageable);

    List<ProductEntity> findFirst15ByOrderByCreatedAtDesc();

}
