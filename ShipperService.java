package com.project.lpd.service;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.ShipperEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ShipperService {
    ShipperEntity creatMember(ShipperEntity  shipperEntity);
    List<ShipperEntity> getAll(Pageable pageable);
    void deleteById(int id);
    Optional<ShipperEntity> find(int id);
    ShipperEntity getShipbyId(int id);

    int getTotaPage(Pageable pageable);
List<ShipperEntity> getShipperByFullName(String name);

}
