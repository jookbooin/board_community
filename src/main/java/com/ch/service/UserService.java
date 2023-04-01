package com.ch.service;

import com.ch.dto.UserDto;

public interface UserService {
    int registerUser(UserDto userDto) throws Exception;

    int removeUser(String id) throws Exception;

    int modifyUser(UserDto userDto) throws Exception;

    UserDto selectUser(String id) throws Exception;


    int idCheck(String check) throws Exception;

    int nicknameCheck(String check) throws Exception;

    int emailCheck(String check) throws Exception;

    int numberCheck(String check) throws Exception;

    int reset() throws Exception;

//    UserDto login(String id, String pwd);

}
