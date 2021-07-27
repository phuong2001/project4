package com.project.lpd.repository;

import com.project.lpd.entity.NewsEntity;
import com.project.lpd.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<UsersEntity, Integer> {
}


