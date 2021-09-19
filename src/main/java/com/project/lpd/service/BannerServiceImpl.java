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
    public BannerEntity showAll() {
        return bannerRepo.findAll().get(bannerRepo.findAll().size() - 1);
    }

    @Override
    public BannerEntity create(BannerEntity bannerEntity) {
        return bannerRepo.save(bannerEntity);
    }
}
