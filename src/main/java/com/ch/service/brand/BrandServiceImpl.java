package com.ch.service.brand;

import com.ch.dao.Brand.BrandDao;
import com.ch.dto.BrandDto;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    BrandDao brandDao;

    public BrandServiceImpl(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    @Override
    public int enroll(BrandDto brandDto) {
        return brandDao.enroll(brandDto);
    }

    @Override
    public int count(String brandName) {
        return brandDao.count(brandName);
    }
}
