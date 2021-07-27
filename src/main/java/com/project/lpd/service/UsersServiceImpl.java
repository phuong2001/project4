package com.project.lpd.service;

import com.project.lpd.entity.UsersEntity;
import com.project.lpd.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepo usersRepo;

    @Override
    public List<UsersEntity> getAllProductUser(Pageable pageable) {
        return usersRepo.findAll(pageable).getContent();
    }


    @Override
    public int getTotalPage(Pageable pageable) {
        return usersRepo.findAll(pageable).getTotalPages();
    }

    @Override
    public UsersEntity getUserById(int id) {
        return usersRepo.findById(id).get();
    }

    @Override
    public UsersEntity createProductUser(UsersEntity p) {
        return usersRepo.save(p);
    }

    @Override
    public void deleteProductUser(int id) {
        usersRepo.deleteById(id);
    }

    @Override
    public UsersEntity updateProductUser(UsersEntity p) {
        return usersRepo.save(p);
    }

}