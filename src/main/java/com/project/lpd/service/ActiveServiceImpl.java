package com.project.lpd.service;

import com.project.lpd.entity.ActiveEntity;
import com.project.lpd.repository.ActiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActiveServiceImpl implements ActiveService{

    @Autowired
    ActiveRepo activeRepo;
    @Override
    public List<ActiveEntity> getAllAc() {
        return activeRepo.findAll();
    }
}
