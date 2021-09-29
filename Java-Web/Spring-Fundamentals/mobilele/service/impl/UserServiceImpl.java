package bgsoftuni.mobilele.service.impl;

import bgsoftuni.mobilele.model.entity.UserEntity;
import bgsoftuni.mobilele.model.service.UserLoginServiceModel;
import bgsoftuni.mobilele.repository.UserRepository;
import bgsoftuni.mobilele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(UserLoginServiceModel userLoginServiceModel) {
        Optional<UserEntity> byUsername = userRepository.findByUsername(userLoginServiceModel.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(userLoginServiceModel.getRawPassword(), byUsername.get().getPassword());
        }
    }

    @Override
    public void initializationUser() {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity();

            admin
                    .setActive(true).
                    setUsername("admin")
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setPassword(passwordEncoder.encode("test"));
            userRepository.save(admin);
            System.out.println("save User !!!!!!!");
        }
    }
}
