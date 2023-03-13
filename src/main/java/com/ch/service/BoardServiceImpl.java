package com.ch.service;


import com.ch.dao.BoardDao;
import com.ch.domain.SearchCondition;
import com.ch.dto.BoardDto;
import com.ch.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    //    @Autowired
    BoardDao boardDao;
    FileService fileService;
    CommentService commentService;


    @Autowired
    public BoardServiceImpl(BoardDao boardDao, FileService fileService, CommentService commentService) {
        this.boardDao = boardDao;
        this.fileService = fileService;
        this.commentService = commentService;
    }

    @Override
    public List<BoardDto> getsearchResultPage(SearchCondition sc) throws Exception {
        return boardDao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return boardDao.searchResultCnt(sc);
    }


    @Override
    public int getCount() throws Exception {
        return boardDao.count();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer bno, String id) throws Exception {
        System.out.println("<BoardService(remove)>");
        System.out.println("bno = " + bno);
        System.out.println("writerId = " + id);

        //게시판 삭제 시 내부 파일 , 댓글 모두 삭제된다.
        fileService.deleteAllFile(bno);
        commentService.deleteAllComments(bno);
        return boardDao.delete(bno, id);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public int write(BoardDto boardDto) throws Exception {
        // 게시글 입력처리와 첨부파일 입력처리가 동시에 일어날 수 있음.
        int bno = boardDao.insert(boardDto);

        System.out.println("<BoardService(write)>");
        System.out.println("bno = " + bno);
        List<FileDto> flist = boardDto.getFileDtolist();
        // 입력 받을때 flist :  [FileDto{Folder='230308', ofname='조합.jpg', sfname='15e9eebe-8619-4d86-ba87-13325458ba7e.jpg', bno=null}....] 이런식
        System.out.println("flist.size : " + flist.size());

        if (flist.size() > 0) {
            for (FileDto fileDto : flist) {
                fileDto.setBno(bno);
                System.out.println("insert fileDto = " + fileDto);
                fileService.insertFile(fileDto);  // file을 등록함으로써 cnt도 증가
            }
        }
        System.out.println("flist : " + flist);
        System.out.println();

        return bno;
    }

    @Override
    public List<BoardDto> getList() throws Exception {
        return boardDao.selectAll();
    }

    @Override
    public BoardDto read(Integer bno) throws Exception {
        BoardDto boardDto = boardDao.select(bno);
        boardDao.increaseViewCnt(bno);
        return boardDto;
    }

    @Override
    public List<BoardDto> getPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }

    @Override
    public int modify(BoardDto boardDto) throws Exception {
        return boardDao.update(boardDto);
    }


}
