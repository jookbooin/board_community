package com.ch.dao;

import com.ch.dto.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private static String namespace = "com.ch.dao.UserDao.";

    @Autowired
    private SqlSession session;

    @Override
    public int insertUser(UserDto userDto) {
        return session.insert(namespace + "insert", userDto);
    }

    @Override
    public int countUser() {
        return session.selectOne(namespace + "count");
    }

    @Override
    public UserDto selectUser(String id) {
        return session.selectOne(namespace + "select", id);
    }

    @Override
    public int updateUser(UserDto userDto) {
        return session.update(namespace + "update", userDto);
    }

    public int deleteUser(String id) {
        return session.delete(namespace + "delete", id);
    }

    @Override
    public int deleteAll() {
        return session.delete(namespace + "deleteAll");
    }


}
