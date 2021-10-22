package com.example.exam12august2020.service.impl;

import com.example.exam12august2020.model.entity.UserEntity;
import com.example.exam12august2020.model.service.UserServiceModel;
import com.example.exam12august2020.repository.UserRepository;
import com.example.exam12august2020.sec.CurrentUser;
import com.example.exam12august2020.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public Boolean freeName(String username) {
        return !userRepository.existsByUsername(username);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        userRepository.save(user);
    }

    @Override
    public UserServiceModel findByEmailAndPassword(String name, String password) {
        return userRepository.findByEmailAndPassword(name, password).map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class)).orElse(null);
    }

    @Override
    public void loginUser(String username, Long id) {
        currentUser.setEmail(username).setId(id);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
