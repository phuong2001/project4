package com.project.lpd.repository;

import com.project.lpd.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepo extends JpaRepository<NewsEntity, Integer> {
    @Query("select p from  NewsEntity  p where p.name like %:name%")
    List<NewsEntity> findByFullName(String name);

    List<NewsEntity> findFirst8ByOrderByCreatedAtDesc();
}


