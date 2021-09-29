package bgsoftuni.mobilele.service.impl;

import bgsoftuni.mobilele.model.entity.BrandEntity;
import bgsoftuni.mobilele.repository.BrandRepository;
import bgsoftuni.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }



    @Override
    public void initializeBrand(){
        if (brandRepository.count() == 0){
            BrandEntity ford = new BrandEntity();
            ford.setName("Ford");

            brandRepository.save(ford);
        }
    }

    @Override
    public BrandEntity findByName(String name){
        return brandRepository.findByName(name)
                .orElseThrow(()->new IllegalArgumentException("No brand with name "+name+" found!"));
    }
}
