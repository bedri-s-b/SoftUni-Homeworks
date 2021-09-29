package bgsoftuni.mobilele.service.impl;

import bgsoftuni.mobilele.model.entity.BrandEntity;
import bgsoftuni.mobilele.model.entity.ModelEntity;
import bgsoftuni.mobilele.model.entity.enums.CategoryEnum;
import bgsoftuni.mobilele.repository.ModelRepository;
import bgsoftuni.mobilele.service.BrandService;
import bgsoftuni.mobilele.service.ModelService;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandService brandService;

    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
    }

    @Override
    public void initializeModels() {
        BrandEntity ford = brandService.findByName("Ford");
        ModelEntity fiesta = new ModelEntity();
        fiesta.setBrand(ford).setName("fiesta").setCategory(CategoryEnum.CAR).setStartYear(1990);
        modelRepository.save(fiesta);
        System.out.println("Save model !!!!");
    }
}
