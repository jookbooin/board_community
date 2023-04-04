package com.ch.service;


import com.ch.domain.SearchCondition;
import com.ch.dto.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<BoardDto> getsearchResultPage(SearchCondition sc);

    int getSearchResultCnt(SearchCondition sc);

    int getCount();

    int remove(Integer bno, String writer) throws Exception;

    void write(BoardDto boardDto) throws Exception;


    List<BoardDto> getList();

    BoardDto read(Integer bno);

    List<BoardDto> getPage(Map map);

    int modify(BoardDto boardDto) throws Exception;
}
