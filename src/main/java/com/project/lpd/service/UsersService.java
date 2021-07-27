package com.project.lpd.service;

import com.project.lpd.entity.NewsEntity;
import com.project.lpd.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UsersService {
    List<UsersEntity> getAllProductUser(Pageable pageable);

    int getTotalPage(Pageable pageable);
    UsersEntity getUserById(int id);
    UsersEntity createProductUser(UsersEntity p);
    void deleteProductUser(int id);
    UsersEntity updateProductUser(UsersEntity p);

}
