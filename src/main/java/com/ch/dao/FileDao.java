package com.ch.dao;

import com.ch.dto.FileDto;

import java.util.List;

public interface FileDao {

    // 게시글 첨부파일 전체 삭제
    public int deleteAllFiles(int bno);

    // 게시글 첨부파일 추가
    public int insertFile(FileDto fileDto) throws Exception;

    // 게시글의 첨부 파일 삭제
    public int deleteFile(Integer fno) throws Exception;

    public int updateFile(FileDto fileDto) throws Exception;

    // 게시글 첨부파일 조회
    List<FileDto> getFilelist(Integer bno) throws Exception;

    FileDto getFile(Integer fno) throws Exception;

    public int FileCnt(Integer bno) throws Exception;
}
