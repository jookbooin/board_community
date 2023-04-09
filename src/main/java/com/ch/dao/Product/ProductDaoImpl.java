package com.ch.dao.Product;

import com.ch.dto.ProductDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {
    private static String namespace = "com.ch.dao.Product.ProductDao.";
    private SqlSession session;

    public ProductDaoImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public int productEnroll(ProductDto productDto) {
        return session.insert(namespace + "productEnroll", productDto);
    }
}
