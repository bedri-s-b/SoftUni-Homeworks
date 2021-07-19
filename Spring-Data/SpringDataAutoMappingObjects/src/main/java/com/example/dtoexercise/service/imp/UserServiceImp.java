package com.example.dtoexercise.service.imp;

import com.example.dtoexercise.model.dto.UserLoginDto;
import com.example.dtoexercise.model.dto.UserRegisterDto;
import com.example.dtoexercise.model.entity.User;
import com.example.dtoexercise.repository.UserRepository;
import com.example.dtoexercise.service.UserService;
import com.example.dtoexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggerUser;

    public UserServiceImp(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.loggerUser = null;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Wrong confirm password");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violation =
                validationUtil.getViolation(userRegisterDto);

        if (!violation.isEmpty()) {
            violation.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        User user = modelMapper.map(userRegisterDto, User.class);
        Long count = userRepository.count();
        if (count == 0){
            user.setAdmin(true);
        }
        this.userRepository.save(user);
        System.out.println(user.getFullName() + " was registered");
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violation =
                validationUtil.getViolation(userLoginDto);
        if (!violation.isEmpty()) {
            violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }
        User user = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword()).orElse(null);

        if (user == null) {
            System.out.println("Incorrect user / password");
            return;
        }
        this.loggerUser = user;
        System.out.println("Successfully logged in " + user.getFullName());


    }

    @Override
    public void logout() {
        if (loggerUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            System.out.println("User " + loggerUser.getFullName() + " successfully logged out");
            loggerUser = null;
        }
    }
}
