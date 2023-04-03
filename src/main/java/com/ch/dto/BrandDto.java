package com.ch.dto;

import lombok.Data;

import java.util.Date;


@Data
public class BrandDto {
    private int brandId;
    private String brandName;

    private String brandIntro;

    private Date reg_date;


    private Date up_date;

    public BrandDto() {
    }

    public BrandDto(String brandName, String brandIntro) {
        this.brandName = brandName;
        this.brandIntro = brandIntro;
    }

}
