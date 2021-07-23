package com.project.lpd.repository;

import com.project.lpd.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepo extends JpaRepository<NewsEntity, Integer> {
}


