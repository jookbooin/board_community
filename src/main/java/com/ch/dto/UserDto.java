package com.ch.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserDto {

    private String id;

    private String pwd;

    private String name;

    private String nickname;

    private String number;
    @NotNull
    @Email(message = "Invalid email address")
    private String email;
    // 우편번호
    private String addr_num;

    //사는 지역
    private String addr_area;
    // 상세주소
    private String addr_detail;
    // 관리자 구분 (일반 : 0 관리자 : 1)
    private int admin;
    private Date reg_date;
}
