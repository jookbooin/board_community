package com.ch.dao;


import com.ch.domain.SearchCondition;
import com.ch.dto.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    int count() // T selectOne(String statement)
    ;

    int deleteAll() // int delete(String statement)
    ;

    int delete(Integer bno, String writer)  // int delete(String statement, Object parameter)
    ;

    int insert(BoardDto dto) // int insert(String statement, Object parameter)
    ;

    List<BoardDto> selectAll() // List<E> selectList(String statement)
    ;

    BoardDto select(Integer bno)  // T selectOne(String statement, Object parameter)
    ;

    List<BoardDto> selectPage(Map map)  // List<E> selectList(String statement, Object parameter)
    ;

    int update(BoardDto dto)  // int update(String statement, Object parameter)
    ;

    int increaseViewCnt(Integer bno)// int update(String statement, Object parameter)
    ;

    public int searchResultCnt(SearchCondition sc);

    public List<BoardDto> searchSelectPage(SearchCondition sc);

    int updateCommentCnt(Integer bno, int i);

    public int updateFileCnt(Integer bno, int i);
}
