package com.ch.dao.Brand;

import com.ch.domain.SearchCondition;
import com.ch.dto.BrandDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BrandDaoImpl implements BrandDao {
    private static String namespace = "com.ch.dao.Brand.BrandDao.";
    private SqlSession session;

    public BrandDaoImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public int enroll(BrandDto brandDto) {
        return session.insert(namespace + "enroll", brandDto);
    }

    @Override
    public int count() {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int check(String brandName) {
        return session.selectOne(namespace + "check", brandName);
    }

    @Override
    public List<BrandDto> selectPage(Map map) {
        return session.selectList(namespace + "selectPage", map);
    }

    @Override
    public int searchResultCnt(SearchCondition sc) {
        return session.selectOne(namespace + "searchResultCnt", sc);
    }

    @Override
    public List<BrandDto> searchSelectPage(SearchCondition sc) {
        return session.selectList(namespace + "searchSelectPage", sc);
    }

    @Override
    public BrandDto brandGetDetail(int brandId) {
        return session.selectOne(namespace + "brandGetDetail", brandId);
    }


}

