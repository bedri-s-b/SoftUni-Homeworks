package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.json.LaptopSeedDto;
import exam.model.entity.Laptop;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {
    private static final String LAPTOP_FILE_NAME = "src/main/resources/files/json/laptops.json";

    private final LaptopRepository laptopRepository;
    private final ShopService shopService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopService shopService, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.laptopRepository = laptopRepository;
        this.shopService = shopService;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(LAPTOP_FILE_NAME));
    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(readLaptopsFileContent(), LaptopSeedDto[].class))
                .filter(laptopSeedDto -> {
                    boolean valid = validationUtil.isValid(laptopSeedDto) &&
                            !existMacAddress(laptopSeedDto.getMacAddress()) &&
                            shopService.existShop(laptopSeedDto.getShop().getName());
                    sb.append(valid ? "Successfully imported Laptop " + laptopSeedDto.getMacAddress() + " - " + laptopSeedDto.getCpuSpeed() + " - " + laptopSeedDto.getRam() + " - " + laptopSeedDto.getStorage()
                            : "Invalid Laptop").append(System.lineSeparator());

                    return valid;
                }).map(laptopSeedDto -> {
                    Laptop laptop = modelMapper.map(laptopSeedDto, Laptop.class);
                    laptop.setShop(shopService.getByName(laptopSeedDto.getShop().getName()));
                    return laptop;
                }).forEach(laptopRepository::save);

        return sb.toString();
    }

    private boolean existMacAddress(String macAddress) {
        return laptopRepository.existsByMacAddress(macAddress);
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder sb = new StringBuilder();

        laptopRepository.exportBestLaptops()
                .forEach(l -> {
                    sb.append("Laptop - ")
                            .append(l.getMacAddress()).append(System.lineSeparator())
                            .append("*Cpu speed - ")
                            .append(l.getCpuSpeed()).append(System.lineSeparator())
                            .append("**Ram - ").append(l.getRam()).append(System.lineSeparator())
                            .append("***Storage - ").append(l.getStorage()).append(System.lineSeparator())
                            .append(String.format("****Price - %.2f", Double.parseDouble(l.getPrice().toString()))).append(System.lineSeparator())
                            .append("#Shop name - ").append(l.getShop().getName()).append(System.lineSeparator())
                            .append("##Town - ").append(l.getShop().getTown().getName())
                            .append(System.lineSeparator())
                            .append(System.lineSeparator());
                });


        return sb.toString();
    }
}
