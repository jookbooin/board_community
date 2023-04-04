package com.ch.service.brand;

import com.ch.dao.Brand.BrandDao;
import com.ch.dto.BrandDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public int check(String brandName) {
        return brandDao.check(brandName);
    }

    @Override
    public int getCount() {
        return brandDao.count();
    }

    @Override
    public List<BrandDto> getPage(Map map) {
        return brandDao.selectPage(map);
    }
}
