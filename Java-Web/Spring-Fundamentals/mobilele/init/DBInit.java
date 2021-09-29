package bgsoftuni.mobilele.init;

import bgsoftuni.mobilele.model.entity.BaseEntity;
import bgsoftuni.mobilele.service.BrandService;
import bgsoftuni.mobilele.service.ModelService;
import bgsoftuni.mobilele.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandService brandService;
    private final ModelService modelService;
    private final UserService userService;

    public DBInit(BrandService brandService, ModelService modelService, UserService userService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        brandService.initializeBrand();
        modelService.initializeModels();
        userService.initializationUser();
    }


}
