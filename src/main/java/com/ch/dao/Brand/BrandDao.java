package com.ch.dao.Brand;

import com.ch.domain.SearchCondition;
import com.ch.dto.BrandDto;

import java.util.List;
import java.util.Map;

public interface BrandDao {
    int enroll(BrandDto brandDto);

    int count();

    int check(String brandName);

    List<BrandDto> selectPage(Map map);

    int searchResultCnt(SearchCondition sc);

    List<BrandDto> searchSelectPage(SearchCondition sc);

    BrandDto brandGetDetail(int brandId);
}
