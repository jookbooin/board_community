package com.ch.dao.Brand;

import com.ch.dto.BrandDto;

public interface BrandDao {
    int enroll(BrandDto brandDto);

    int count(String brandName);
}
