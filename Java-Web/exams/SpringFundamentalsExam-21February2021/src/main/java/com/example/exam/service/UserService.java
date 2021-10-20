package com.example.exam.service;

import com.example.exam.model.entity.UserEntity;
import com.example.exam.model.service.UserServiceModel;

public interface UserService {
    boolean userNameFree(String username);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findFyUsernameAndPassword(String username, String password);

    void loginUser(String username, Long id);


    UserEntity findById(Long id);
}
