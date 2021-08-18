package exam.service.impl;

import exam.model.dto.xml.ShopSeedRootDto;
import exam.model.entity.Shop;
import exam.repository.ShopRepository;
import exam.service.ShopService;
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

@Service
public class ShopServiceImpl implements ShopService {
    private static final String SHOP_FILE_PATH = "src/main/resources/files/xml/shops.xml";

    private final ShopRepository shopRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public ShopServiceImpl(ShopRepository shopRepository, TownService townService, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.shopRepository = shopRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(SHOP_FILE_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        xmlParser.readFromFile(SHOP_FILE_PATH, ShopSeedRootDto.class)
                .getShops()
                .stream()
                .filter(shopSeedDto -> {
                    boolean valid = validationUtil.isValid(shopSeedDto) &&
                            !existShop(shopSeedDto.getName())&&
                            townService.existByName(shopSeedDto.getTown().getName());

                    sb.append(valid ? "Successfully imported Shop " + shopSeedDto.getName() + " - " + shopSeedDto.getIncome()
                            : "Invalid shop").append(System.lineSeparator());

                    return valid;
                })
                .map(shopSeedDto -> {
                    Shop shop = modelMapper.map(shopSeedDto, Shop.class);
                    shop.setTown(townService.getTown(shopSeedDto.getTown().getName()));
                    return shop;
                })
                .forEach(shopRepository::save);

        return sb.toString();
    }

    @Override
    public boolean existShop(String name) {
        return shopRepository.existsByName(name);
    }

    @Override
    public Shop getByName(String name){
        return shopRepository.findByName(name);
    }
}
