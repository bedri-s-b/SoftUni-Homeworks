package com.example.exam_web.service.impl;

import com.example.exam_web.model.entity.CategoryEntity;
import com.example.exam_web.model.entity.ShipEntity;
import com.example.exam_web.model.entity.UserEntity;
import com.example.exam_web.model.service.ShipServiceModel;
import com.example.exam_web.model.view.ShipViewModel;
import com.example.exam_web.repository.CategoryRepository;
import com.example.exam_web.repository.ShipRepository;
import com.example.exam_web.repository.UserRepository;
import com.example.exam_web.sec.CurrentUser;
import com.example.exam_web.service.ShipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ShipRepository shipRepository;

    public ShipServiceImpl(ModelMapper modelMapper, CurrentUser currentUser, UserRepository userRepository, CategoryRepository categoryRepository, ShipRepository shipRepository) {
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.shipRepository = shipRepository;
    }

    @Override
    public void addShip(ShipServiceModel shipServiceModel) {
        ShipEntity ship = modelMapper.map(shipServiceModel,ShipEntity.class);
        Long id = currentUser.getId();
        UserEntity user = userRepository.getById(id);
        CategoryEntity category = categoryRepository.findByName(shipServiceModel.getCategory());
        ship.setUser(user).setCategory(category);
        shipRepository.save(ship);
    }

    @Override
    public List<ShipViewModel> findAllShipWithId(Long id) {
        return shipRepository.findAllById(id).stream().map(s -> modelMapper.map(s,ShipViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShipViewModel> findOtherShip(Long id) {
        return shipRepository.findAll().stream().filter(shipEntity -> shipEntity.getId() != id)
                .sorted((a,b) -> {
                  if (!a.getName().equals(b.getName())){
                      return a.getName().compareTo(b.getName());
                  }else if (a.getHealth() != b.getHealth()){
                      return a.getHealth() - b.getHealth();
                  } else {
                    return   a.getPower() - b.getPower();
                  }
                }).map(s -> modelMapper.map(s,ShipViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShipViewModel> findAllShips() {
        return shipRepository.findAll().stream().sorted((a,b) -> {
            if (!a.getName().equals(b.getName())){
                return a.getName().compareTo(b.getName());
            }else if (a.getHealth() != b.getHealth()){
                return a.getHealth() - b.getHealth();
            } else {
                return   a.getPower() - b.getPower();
            }
        }).map(s -> modelMapper.map(s,ShipViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void fire(Long idCurrent, Long idOther) {
        int afterAttack = shipRepository.findById(idOther).get().getHealth() - shipRepository.findById(idCurrent).get().getPower();

        if (afterAttack <= 0){
            shipRepository.deleteById(idOther);
        }
        shipRepository.reduceHealth(afterAttack,idOther);
    }
}
