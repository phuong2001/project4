package com.project.lpd.service;

import com.project.lpd.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<Image> createImage(List<Image> images);
    List<Image> upDateImage(List<Image>images);
}
