package com.ch.service;


import com.ch.domain.SearchCondition;
import com.ch.dto.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<BoardDto> getsearchResultPage(SearchCondition sc) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;

    int getCount() throws Exception;

    int remove(Integer bno, String writer) throws Exception;

    int write(BoardDto boardDto) throws Exception;


    List<BoardDto> getList() throws Exception;

    BoardDto read(Integer bno) throws Exception;

    List<BoardDto> getPage(Map map) throws Exception;

    int modify(BoardDto boardDto) throws Exception;
}
