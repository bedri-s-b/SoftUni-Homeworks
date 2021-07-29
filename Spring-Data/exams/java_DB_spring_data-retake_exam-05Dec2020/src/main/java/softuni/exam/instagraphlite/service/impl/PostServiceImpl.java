package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PostSeedRootDto;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PostServiceImpl implements PostService {

    private static final String POST_FILE_PATH = "src/main/resources/files/posts.xml";

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final PictureService pictureService;
    private final UserService userService;


    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, XmlParser xmlParser, ValidationUtil validationUtil, PictureService pictureService, UserService userService) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.pictureService = pictureService;
        this.userService = userService;
    }


    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POST_FILE_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        PostSeedRootDto postSeedRootDto = xmlParser.readFromFile(POST_FILE_PATH, PostSeedRootDto.class);

        postSeedRootDto.getPosts().stream()
                .filter(postSeedDto -> {
                    boolean isValid = validationUtil.isValid(postSeedDto) &&
                            pictureService.entityExist(postSeedDto.getPicture().getPath()) &&
                            userService.existUserName(postSeedDto.getUser().getName());
                    sb.append(isValid ? "Successfully imported Post, made by " + postSeedDto.getUser().getName()
                            : "Invalid Post").append(System.lineSeparator());
                    return isValid;
                })
                .map(postSeedDto -> {
                    Post post = mapper.map(postSeedDto, Post.class);
                    post.setPicture(pictureService.findPic(postSeedDto.getPicture().getPath()));
                    post.setUser(userService.findUserByName(postSeedDto.getUser().getName()));
                    return post;
                })
                .forEach(postRepository::save);
        return sb.toString();
    }
}
