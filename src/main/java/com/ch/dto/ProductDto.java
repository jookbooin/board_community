package com.ch.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ProductDto {
    private int productId;
    private String productName;
    private int brandId;
    private String brandName;
    private String cateCode;
    private String cateName;
    private int productPrice;
    private int productStock;
    private double productDiscount;
    private String productIntro;
    private String productContents;
    private Date reg_date;
    private Date up_date;

    public ProductDto(String productName, int brandId, String brandName, String cateCode, String cateName, int productPrice, int productStock, double productDiscount, String productIntro, String productContents) {
        this.productName = productName;
        this.brandId = brandId;
        this.brandName = brandName;
        this.cateCode = cateCode;
        this.cateName = cateName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDiscount = productDiscount;
        this.productIntro = productIntro;
        this.productContents = productContents;
    }
}
