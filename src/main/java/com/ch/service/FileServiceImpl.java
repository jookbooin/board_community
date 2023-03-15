package com.ch.service;

import com.ch.dao.BoardDao;
import com.ch.dao.FileDao;
import com.ch.dto.FileDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    FileDao fileDao;
    BoardDao boardDao;

    FileServiceImpl(FileDao fileDao, BoardDao boardDao) {
        this.fileDao = fileDao;
        this.boardDao = boardDao;
    }

    // 첨부파일 목록가져오기 -> String 일까 FileDto일까??
    @Override
    public List<FileDto> getBoardFiles(Integer bno) throws Exception {
        return fileDao.getFilelist(bno);
    }

    @Override
    public FileDto getFile(Integer fno) throws Exception {
        FileDto fileDto = fileDao.getFile(fno);
        if (fileDto == null) {
            throw new Exception("첨부파일 [" + fno + "]에 대한 조회 실패");
        }
        return fileDto;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteFile(FileDto fileDto) throws Exception {
        boardDao.updateFileCnt(fileDto.getBno(), -1);
        int rowCnt = fileDao.deleteFile(fileDto.getFno());
        return rowCnt;
    }

    @Override
    public int deleteAllFile(Integer bno) throws Exception {
        return fileDao.deleteAllFiles(bno);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertFile(FileDto fileDto) throws Exception {

        int rowCnt = fileDao.insertFile(fileDto);
        System.out.println("(insertFile) fileDto = " + fileDto);
        boardDao.updateFileCnt(fileDto.getBno(), 1);
        return rowCnt;

    }

    @Override
    public int updateFile(FileDto fileDto) throws Exception {
        return fileDao.updateFile(fileDto);
    }
}
