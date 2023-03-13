package com.ch.dao;


import com.ch.dto.CommentDto;

import java.util.List;

public interface CommentDao {
    int delete(Integer cno, String commenterId);

    int deleteAll(Integer bno) // int delete(String statement)
    ;

    int count(Integer bno);

    int insert(CommentDto commentDto);

    CommentDto select(Integer cno);

    List<CommentDto> selectAll(Integer bno);

    int update(CommentDto commentDto) throws Exception;
}
