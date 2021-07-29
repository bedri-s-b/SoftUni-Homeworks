package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_FILE_PATH = "src/main/resources/files/users.json";

    private final UserRepository userRepository;
    private final PictureService pictureService;
    private final ModelMapper mapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, PictureService pictureService, ModelMapper mapper, Gson gson, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.pictureService = pictureService;
        this.mapper = mapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USER_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        StringBuilder sb = new StringBuilder();

        UserSeedDto[] userSeedDtos = gson.fromJson(readFromFileContent(), UserSeedDto[].class);

        Arrays.stream(userSeedDtos)
                .filter(userSeedDto -> {
                    boolean isValid = validationUtil.isValid(userSeedDto) &&
                            pictureService.entityExist(userSeedDto.getProfilePicture()) &&
                            !existUserName(userSeedDto.getUserName());

                    sb.append(isValid ? "Successfully imported User: " + userSeedDto.getUserName()
                            : "Invalid User").append(System.lineSeparator());

                    return isValid;
                }).map(userSeedDto -> {
            User user = mapper.map(userSeedDto, User.class);
            Picture pic = pictureService.findPic(userSeedDto.getProfilePicture());
            user.setProfilePicture(pic);
            return user;
        })
                .forEach(userRepository::save);


        return sb.toString();
    }

    @Override
    public boolean existUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public String exportUsersWithTheirPosts() {
        StringBuilder sb = new StringBuilder();

        List<User> allByPostCount = userRepository.findAllByPostCount();

        allByPostCount
                .forEach(user -> {
                    sb.append("User: ")
                            .append(user.getUserName())
                            .append(System.lineSeparator())
                            .append("Post count: ")
                            .append(user.getPosts().size())
                            .append(System.lineSeparator());

                    user.getPosts().stream()
                            .sorted((p1,p2) -> (int) (p1.getPicture().getSize() - p2.getPicture().getSize()))
                            .forEach(post -> {
                        sb.append("==Post Details: ")
                                .append(System.lineSeparator())
                                .append("----Caption: ")
                                .append(post.getCaption())
                                .append(System.lineSeparator())
                                .append("----Picture Size: ")
                                .append(post.getPicture().getSize())
                                .append(System.lineSeparator());
                    });
                });

        return sb.toString();
    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findByUserName(username);
    }
}
