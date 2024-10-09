package com.e_commerce.service.impl;

import com.e_commerce.model.Brand;
import com.e_commerce.repository.BrandRepository;
import com.e_commerce.service.BrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }
}
