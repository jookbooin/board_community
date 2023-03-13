package com.ch.dao;


import com.ch.dto.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentDaoImpl implements CommentDao {
    private static String namespace = "com.ch.dao.CommentDao.";
    @Autowired
    private SqlSession session;

    @Override
    public int delete(Integer cno, String commenterId) {
        Map map = new HashMap();
        map.put("cno", cno);
        map.put("commenterId", commenterId);
        return session.delete(namespace + "delete", map);
    }

    @Override
    public int deleteAll(Integer bno) {
        return session.delete(namespace + "deleteAll", bno);
    } // int delete(String statement)

    @Override
    public int count(Integer bno) {
        return session.selectOne(namespace + "count", bno);
    }

    @Override
    public int insert(CommentDto commentDto) {
        return session.insert(namespace + "insert", commentDto);
    }

    @Override
    public CommentDto select(Integer cno) {
        return session.selectOne(namespace + "select", cno);
    }

    @Override
    public List<CommentDto> selectAll(Integer bno) {
        return session.selectList(namespace + "selectAll", bno);
    }

    @Override
    public int update(CommentDto commentDto) throws Exception {
        return session.update(namespace + "update", commentDto);
    }


}
