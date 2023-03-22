package com.ch.service;

import com.ch.dao.UserDao;
import com.ch.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    //    @Autowired
    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int registerUser(UserDto userDto) throws Exception {
        return userDao.insertUser(userDto);
    }

    @Override
    public int removeUser(String id) throws Exception {
        return userDao.deleteUser(id);
    }

    @Override
    public int modifyUser(UserDto userDto) throws Exception {
        return userDao.updateUser(userDto);
    }

    @Override
    public UserDto selectUser(String id) throws Exception {
        return userDao.selectUser(id);
    }

    @Override
    public int reset() throws Exception {
        return userDao.deleteAll();
    }

    @Override
    public int idCheck(String id) throws Exception {
        return userDao.idCheck(id);
    }


}
