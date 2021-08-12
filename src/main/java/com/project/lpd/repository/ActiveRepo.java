package com.project.lpd.repository;

import com.project.lpd.entity.ActiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveRepo extends JpaRepository<ActiveEntity,Integer> {
}
