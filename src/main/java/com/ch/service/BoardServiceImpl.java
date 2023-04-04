package com.ch.service;


import com.ch.dao.BoardDao;
import com.ch.domain.SearchCondition;
import com.ch.dto.BoardDto;
import com.ch.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
    public List<BoardDto> getsearchResultPage(SearchCondition sc) {
        return boardDao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) {
        return boardDao.searchResultCnt(sc);
    }


    @Override
    public int getCount() {
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
    @Transactional(rollbackFor = Exception.class)
    public void write(BoardDto boardDto) throws Exception {
        // 게시글 입력처리와 첨부파일 입력처리가 동시에 일어날 수 있음.
        boardDao.insert(boardDto);
        int bno = boardDto.getBno();

        System.out.println("<BoardService(write)>");
        System.out.println("bno = " + bno);

// 형식: flist : [FileDto{fno='null', Folder='resources\\upload\230316', ofname='head ccc.txt.jpg', sfname='56a9d38b-e325-4531-b5b4-ba1c102ed948.jpg', bno=136, upload_date=null}
        List<FileDto> flist = boardDto.getFileDtolist();
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
    }

    @Override
    public List<BoardDto> getList() {
        return boardDao.selectAll();
    }

    @Override
    public BoardDto read(Integer bno) {
        BoardDto boardDto = boardDao.select(bno);
        boardDao.increaseViewCnt(bno);
        return boardDto;
    }

    @Override
    public List<BoardDto> getPage(Map map) {
        return boardDao.selectPage(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modify(BoardDto boardDto) throws Exception {
        System.out.println();
        System.out.println("boardDto = " + boardDto);

        int delFno[] = boardDto.getDelFno();
        System.out.println("delFno : " + Arrays.toString(delFno)); // 자동으로 불러옴

        // board 내부에서 삭제 된다면 DB에서 지운다
        if (delFno != null && delFno.length > 0) {
            for (int fno : delFno) {
                fileService.deleteFile(fno, boardDto.getBno());
                System.out.println("파일을 삭제했습니다.");
            }
        }

        // JSP 에서 추가된 파일배열
        for (FileDto f : boardDto.getFileDtolist()) {
            f.setBno(boardDto.getBno());
            fileService.insertFile(f);
            System.out.println("파일을 넣었습니다.");
        }

        boardDto.setFileDtolist(fileService.getBoardFiles(boardDto.getBno()));

        return boardDao.update(boardDto);
    }


}
