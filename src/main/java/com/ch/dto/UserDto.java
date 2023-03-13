package com.ch.dto;

import java.util.Date;
import java.util.Objects;

public class UserDto {
    private String id;
    private String pwd;
    private String name;
    private String nickname;
    private String number;
    private String email;
    private Date birth;
    private Date reg_date;

    public UserDto() {
    }

    public UserDto(String id, String pwd, String name, String nickname, String number, String email, Date birth, Date reg_date) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
        this.number = number;
        this.email = email;
        this.birth = birth;
        this.reg_date = reg_date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return "UserDto={" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", reg_date=" + reg_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto user = (UserDto) o;
        return Objects.equals(id, user.id) && Objects.equals(pwd, user.pwd) && Objects.equals(name, user.name) && Objects.equals(nickname, user.nickname) && Objects.equals(email, user.email) && Objects.equals(birth, user.birth) && Objects.equals(reg_date, user.reg_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pwd, name, nickname, email, birth, reg_date);
    }
}
