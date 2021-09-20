package com.project.lpd.service;

import com.project.lpd.entity.BannerEntity;
import com.project.lpd.repository.BannerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerRepo bannerRepo;

    @Override
    public List<BannerEntity> showAll() {
        return bannerRepo.findAll();
    }

    @Override
    public BannerEntity create(BannerEntity bannerEntity) {
        return bannerRepo.save(bannerEntity);
    }

    @Override
    public BannerEntity updateBanner(BannerEntity banner) {
        return bannerRepo.save(banner);
    }

    @Override
    public BannerEntity getBannerById(int id) {
        return bannerRepo.getById(id);
    }

}
