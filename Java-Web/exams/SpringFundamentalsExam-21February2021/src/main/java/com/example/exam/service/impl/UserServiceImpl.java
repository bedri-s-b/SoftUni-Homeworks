package com.example.exam.service.impl;

import com.example.exam.model.entity.UserEntity;
import com.example.exam.model.service.UserServiceModel;
import com.example.exam.repository.UserRepository;
import com.example.exam.sec.CurrentUser;
import com.example.exam.service.UserService;
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
    public boolean userNameFree(String username) {
        return !userRepository.existsByUsername(username);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        userRepository.save(user);
    }

    @Override
    public UserServiceModel findFyUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class)).orElse(null);
    }

    @Override
    public void loginUser(String username, Long id) {
        currentUser.setUsername(username).setId(id);
    }

    @Override
    public UserEntity findById(Long id) {
        return
                userRepository.findById(id).orElse(null);
    }
}
