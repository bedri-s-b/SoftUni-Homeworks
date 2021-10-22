package com.example.exam12august2020.service;

import com.example.exam12august2020.model.entity.UserEntity;
import com.example.exam12august2020.model.service.UserServiceModel;

public interface UserService {
    Boolean freeName(String username);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByEmailAndPassword(String email, String password);

    void loginUser(String username, Long id);

    UserEntity findById(Long id);
}
