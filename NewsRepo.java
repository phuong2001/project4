package com.project.lpd.repository;

import com.project.lpd.entity.NewsEntity;
import com.project.lpd.entity.ProductUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepo extends JpaRepository<NewsEntity, Integer> {
    @Query("select b from NewsEntity b where b.name like %:name%")
    List<NewsEntity> findByFullName(String name);
}


