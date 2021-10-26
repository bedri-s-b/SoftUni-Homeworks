package com.example.exam_web.service;

import com.example.exam_web.model.service.UserServiceModel;

public interface UserService {
    boolean freeUsername(String username);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(String username, Long id);
}
