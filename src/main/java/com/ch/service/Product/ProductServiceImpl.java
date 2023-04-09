package com.ch.service.Product;

import com.ch.dao.Product.ProductDao;
import com.ch.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductDao productDao;

    @Override
    public int productEnroll(ProductDto productDto) {
        log.info("상품등록");
        return productDao.productEnroll(productDto);
    }
}
