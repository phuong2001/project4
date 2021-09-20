package com.project.lpd.service;
import com.project.lpd.entity.BannerEntity;
import com.project.lpd.repository.BannerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BannerService {
    List<BannerEntity> showAll();
    BannerEntity create(BannerEntity bannerEntity);
    BannerEntity updateBanner(BannerEntity b);
    BannerEntity getBannerById(int id);

}
