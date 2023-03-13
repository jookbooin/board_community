package com.ch.service;

import com.ch.dto.UserDto;

public interface UserService {
    int registerUser(UserDto userDto) throws Exception;

    int removeUser(String id) throws Exception;

    int modifyUser(UserDto userDto) throws Exception;

    UserDto selectUser(String id) throws Exception;

    public int reset() throws Exception;
}
