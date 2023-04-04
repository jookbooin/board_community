package com.ch.dao.Brand;

import com.ch.dto.BrandDto;

import java.util.List;
import java.util.Map;

public interface BrandDao {
    int enroll(BrandDto brandDto);

    int count();

    int check(String brandName);

    List<BrandDto> selectPage(Map map);
}
