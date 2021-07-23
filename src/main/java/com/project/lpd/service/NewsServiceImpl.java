package com.project.lpd.service;

import com.project.lpd.entity.NewsEntity;
import com.project.lpd.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    NewsRepo newsRepo;

    @Override
    public List<NewsEntity> getAllNews(Pageable pageable) {
        return newsRepo.findAll(pageable).getContent();
    }


    @Override
    public int getTotalPage(Pageable pageable) {
        return newsRepo.findAll(pageable).getTotalPages();
    }

    @Override
    public NewsEntity getNewById(int id) {
        return newsRepo.findById(id).get();
    }

    @Override
    public NewsEntity createNew(NewsEntity p) {
        return newsRepo.save(p);
    }

    @Override
    public void deleteNew(int id) {
        newsRepo.deleteById(id);
    }

    @Override
    public NewsEntity updateNew(NewsEntity p) {
        return newsRepo.save(p);
    }
}