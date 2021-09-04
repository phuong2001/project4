package com.project.lpd.repository;

import com.project.lpd.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface ImageRepo extends JpaRepository<Image, Integer> {
}
