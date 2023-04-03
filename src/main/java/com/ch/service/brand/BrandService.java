package com.ch.service.brand;

import com.ch.dto.BrandDto;

public interface BrandService {
    int enroll(BrandDto brandDto);

    int count(String brandName);
}
