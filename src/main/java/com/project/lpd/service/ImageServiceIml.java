package com.project.lpd.service;

import com.project.lpd.entity.Image;
import com.project.lpd.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageServiceIml implements ImageService {

    @Autowired
    ImageRepo imageRepo;


    @Override
    public List<Image> createImage(List<Image> image) {
        return imageRepo.saveAll(image);
    }
}
