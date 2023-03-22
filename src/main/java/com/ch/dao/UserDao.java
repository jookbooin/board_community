package com.ch.dao;

import com.ch.dto.UserDto;

public interface UserDao {
    int insertUser(UserDto userDto);

    int countUser();

    UserDto selectUser(String id);

    int updateUser(UserDto userDto) throws Exception;

    int deleteUser(String id);

    int deleteAll();

    public int idCheck(String check);

    public int nicknameCheck(String check);

    public int emailCheck(String check);

    public int numberCheck(String check);

}
