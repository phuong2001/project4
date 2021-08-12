package com.project.lpd.service;

import com.project.lpd.entity.ShipActivateEntity;
import com.project.lpd.repository.ActivateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivateServiceImpl implements ActivateService {
    @Autowired
    ActivateRepo activateRepo;
    @Override
    public List<ShipActivateEntity> getall() {
        return activateRepo.findAll();
    }
}

