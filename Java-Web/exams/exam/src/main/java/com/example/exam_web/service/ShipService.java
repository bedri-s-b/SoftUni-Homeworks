package com.example.exam_web.service;

import com.example.exam_web.model.service.ShipServiceModel;
import com.example.exam_web.model.view.ShipViewModel;

import java.util.List;

public interface ShipService {
    void addShip(ShipServiceModel shipServiceModel);

    List<ShipViewModel> findAllShipWithId(Long id);

    List<ShipViewModel> findOtherShip(Long id);


    List<ShipViewModel> findAllShips();


    void fire(Long idCurrent, Long idOther);
}
