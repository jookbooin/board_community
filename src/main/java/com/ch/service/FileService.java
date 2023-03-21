package com.ch.service;

import com.ch.dto.FileDto;

import java.util.List;

public interface FileService {

    List<FileDto> getBoardFiles(Integer bno) throws Exception;

    FileDto getFile(Integer fno) throws Exception;

    public int deleteFile(Integer fno, Integer bno) throws Exception;

    public int deleteAllFile(Integer bno) throws Exception;

    public int insertFile(FileDto fileDto) throws Exception;


    public int updateFile(FileDto fileDto) throws Exception;


}
