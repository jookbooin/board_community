package com.ch.service.brand;

import com.ch.domain.SearchCondition;
import com.ch.dto.BrandDto;

import java.util.List;
import java.util.Map;

public interface BrandService {
    int enroll(BrandDto brandDto);

    int check(String brandName);

    int getCount();

    public List<BrandDto> getPage(Map map);

    List<BrandDto> getsearchResultPage(SearchCondition sc);

    int getSearchResultCnt(SearchCondition sc);

    BrandDto brandGetDetail(int brandId);
}
