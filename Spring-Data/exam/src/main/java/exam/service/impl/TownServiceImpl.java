package exam.service.impl;

import exam.model.dto.xml.TownsSeedRootDto;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWN_FILE_PATH = "src/main/resources/files/xml/towns.xml";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWN_FILE_PATH));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        xmlParser.readFromFile(TOWN_FILE_PATH, TownsSeedRootDto.class)
                .getTowns()
                .stream()
                .filter(townsSeedDto -> {
                    boolean valid = validationUtil.isValid(townsSeedDto) &&
                            !townRepository.existsByName(townsSeedDto.getName());
                    sb.append(valid ? "Successfully imported Town " + townsSeedDto.getName()
                            : "Invalid town").append(System.lineSeparator());

                    return valid;
                })
                .map(townsSeedDto -> modelMapper.map(townsSeedDto, Town.class))
                .forEach(townRepository::save);

        return sb.toString();
    }

    @Override
    public boolean existByName(String name) {
        return townRepository.existsByName(name);
    }

    @Override
    public Town getTown(String name) {
        return townRepository.findByName(name);
    }
}
