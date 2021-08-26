package com.project.lpd.service;

import com.project.lpd.entity.RoleEntity;
import com.project.lpd.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepo roleRepo;

    @Override
    public List<RoleEntity> getAllRole(Pageable pageable) {
        return roleRepo.findAll(pageable).getContent();
    }

    @Override
    public int getTotalPage(Pageable pageable) {
        return roleRepo.findAll(pageable).getTotalPages();
    }

    @Override
    public RoleEntity getRoleById(int id) {
        return roleRepo.findById(id).get();
    }

    @Override
    public RoleEntity updateRole(RoleEntity p) {
        return roleRepo.save(p);
    }
}
