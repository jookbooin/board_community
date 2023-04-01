package com.ch.dto;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class UserDto {

    private String id;

    private String pwd;

    private String name;

    private String nickname;

    private String number;
    @NotNull
    @Email(message = "Invalid email address")
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    // 우편번호

    private String addr_num;

    //사는 지역
    private String addr_area;
    // 상세주소
    private String addr_detail;
    // 관리자 구분 (일반 : 0 관리자 : 1)
    private int admin;
    private Date reg_date;

    public UserDto() {
    }

    public UserDto(String id, String pwd, String name, String nickname, String number, String email, Date birth, String addr_num, String addr_area, String addr_detail, int admin) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
        this.number = number;
        this.email = email;
        this.birth = birth;
        this.addr_num = addr_num;
        this.addr_area = addr_area;
        this.addr_detail = addr_detail;
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddr_num() {
        return addr_num;
    }

    public void setAddr_num(String addr_num) {
        this.addr_num = addr_num;
    }

    public String getAddr_area() {
        return addr_area;
    }

    public void setAddr_area(String addr_area) {
        this.addr_area = addr_area;
    }

    public String getAddr_detail() {
        return addr_detail;
    }

    public void setAddr_detail(String addr_detail) {
        this.addr_detail = addr_detail;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return admin == userDto.admin && Objects.equals(id, userDto.id) && Objects.equals(pwd, userDto.pwd) && Objects.equals(name, userDto.name) && Objects.equals(nickname, userDto.nickname) && Objects.equals(number, userDto.number) && Objects.equals(email, userDto.email) && Objects.equals(birth, userDto.birth) && Objects.equals(addr_num, userDto.addr_num) && Objects.equals(addr_area, userDto.addr_area) && Objects.equals(addr_detail, userDto.addr_detail) && Objects.equals(reg_date, userDto.reg_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pwd, name, nickname, number, email, birth, addr_num, addr_area, addr_detail, admin, reg_date);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", addr_num='" + addr_num + '\'' +
                ", addr_area='" + addr_area + '\'' +
                ", addr_detail='" + addr_detail + '\'' +
                ", admin=" + admin +
                ", reg_date=" + reg_date +
                '}';
    }
}
