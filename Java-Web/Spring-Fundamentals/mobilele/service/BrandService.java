package bgsoftuni.mobilele.service;

import bgsoftuni.mobilele.model.entity.BrandEntity;
import org.springframework.stereotype.Service;

public interface BrandService {

    void initializeBrand();

    BrandEntity findByName(String name);
}
