package com.project.lpd.service;

import com.project.lpd.entity.RoleEntity;
import org.springframework.data.domain.Pageable;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;

public interface RoleService {
    List<RoleEntity> getAllRole(Pageable pageable);
    int getTotalPage(Pageable pageable);
    RoleEntity getRoleById(int id);
    RoleEntity updateRole(RoleEntity p);
}
