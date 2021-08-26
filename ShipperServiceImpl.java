package com.project.lpd.service;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.ShipperEntity;
import com.project.lpd.repository.ShippeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipperServiceImpl implements ShipperService{

    @Autowired
    ShippeRepo shippeRepo;
    @Override
    public ShipperEntity creatMember(ShipperEntity shipperEntity) {
        return shippeRepo.save(shipperEntity);
    }

    @Override
    public List<ShipperEntity> getAll(Pageable pageable) {
        return shippeRepo.findAll(pageable).getContent();
    }

    @Override
    public void deleteById(int id) {
        shippeRepo.deleteById(id);
    }

    @Override
    public Optional<ShipperEntity> find(int id) {
        return shippeRepo.findById(id);
    }

    @Override
    public ShipperEntity getShipbyId(int id) {
        return shippeRepo.getById(id);
    }

    @Override
    public int getTotaPage(Pageable pageable) {
        return shippeRepo.findAll(pageable).getTotalPages();
    }

    @Override
    public List<ShipperEntity> getShipperByFullName(String name) {
        return shippeRepo.findByFullName(name);
    }


}
