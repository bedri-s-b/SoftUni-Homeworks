package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PictureSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURE_PATH_FILE = "src/main/resources/files/pictures.json";

    private final PictureRepository pictureRepository;

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PICTURE_PATH_FILE));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        PictureSeedDto[] seedDtos = gson
                .fromJson(readFromFileContent(), PictureSeedDto[].class);
        Arrays.stream(seedDtos).
                filter(seedDto -> {
                    boolean isValid = validationUtil.isValid(seedDto) &&
                            !entityExist(seedDto.getPath());
                    sb.append(isValid ? String.format("Successfully imported Picture, with size %.2f", seedDto.getSize())
                            : "Invalid Picture").append(System.lineSeparator());

                    return isValid;
                })
                .map(seedDto -> mapper.map(seedDto, Picture.class))
                .forEach(pictureRepository::save);
        return sb.toString();
    }

    @Override
    public boolean entityExist(String path) {
        return pictureRepository.existsByPath(path);

    }

    @Override
    public String exportPictures() {
        StringBuilder sb = new StringBuilder();

        double size = 30000;
        List<Picture> bigger = pictureRepository.findAllBySizeBigger(size);

        bigger.forEach(pic -> sb.append(String.format("%.2f",pic.getSize())).append(" – ").append(pic.getPath()).append(System.lineSeparator()));

        return sb.toString();
    }

    @Override
    public Picture findPic(String path) {
        return pictureRepository.findByPath(path);
    }
}
