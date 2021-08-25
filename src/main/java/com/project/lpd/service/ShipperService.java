package com.project.lpd.service;

import com.project.lpd.entity.ShipperEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ShipperService {
    ShipperEntity creatMember(ShipperEntity  shipperEntity);
    List<ShipperEntity> getAll();
    void deleteById(int id);
    Optional<ShipperEntity> find(int id);

    List<ShipperEntity> findByNameShipper(String name);
}
