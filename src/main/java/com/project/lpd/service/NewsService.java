package com.project.lpd.service;

import com.project.lpd.entity.NewsEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    List<NewsEntity> getAllNews(Pageable pageable);
    int getTotalPage(Pageable pageable);
    NewsEntity getNewById(int id);
    NewsEntity createNew(NewsEntity p);
    void deleteNew(int id);
    NewsEntity updateNew(NewsEntity p);
}
