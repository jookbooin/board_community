package com.ch.dao;


import com.ch.domain.SearchCondition;
import com.ch.dto.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {
    private static String namespace = "com.ch.dao.BoardDao.";

    private SqlSession session;

    BoardDaoImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    } // T selectOne(String statement)

    @Override
    public int deleteAll() {
        return session.delete(namespace + "deleteAll");
    } // int delete(String statement)

    @Override
    public int delete(Integer bno, String id) throws Exception {
        System.out.println("<BoardDaoImpl>");
        System.out.println("bno = " + bno);
        System.out.println("writerId = " + id);

        Map map = new HashMap();
        map.put("bno", bno);
        map.put("id", id);

        return session.delete(namespace + "delete", map);
    } // int delete(String statement, Object parameter)

    @Override
    public int insert(BoardDto boardDto) throws Exception {
        session.insert(namespace + "insert", boardDto);
        // boardMapper에 selectKey를 이용해서 boardDto 의 bno에 Auto-increment 된 값을 바로 넣었다.
        // 원래 boardDto bno = null이고 DB에서 bno가 생성되는 것이지만
        // selectKey를 통해서는 bno에 DB에 insert된 bno 값을 바로 불러올 수 있다.
        // boardService 까지 전달하기 위해서 ( file로 bno를 전달하는 곳 ) bno를 반환해준다 .
        return boardDto.getBno();
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    } // List<E> selectList(String statement)

    @Override
    public BoardDto select(Integer bno) throws Exception {
        return session.selectOne(namespace + "select", bno);
    } // T selectOne(String statement, Object parameter)

    @Override
    public List<BoardDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace + "selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

    @Override
    public int update(BoardDto dto) throws Exception {
        return session.update(namespace + "update", dto);
    } // int update(String statement, Object parameter)

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace + "increaseViewCnt", bno);
    } // int update(String statement, Object parameter)

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
        return session.selectOne(namespace + "searchResultCnt", sc);
    }

    @Override
    public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace + "searchSelectPage", sc);
    }

    @Override
    public int updateCommentCnt(Integer bno, int cnt) {
        Map map = new HashMap();
        map.put("cnt", cnt);
        map.put("bno", bno);
        return session.update(namespace + "updateCommentCnt", map);
    }

    @Override
    public int updateFileCnt(Integer bno, int cnt) {
        Map map = new HashMap();
        map.put("cnt", cnt);
        map.put("bno", bno);
        return session.update(namespace + "updateFileCnt", map);
    }
}
