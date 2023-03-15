package com.ch.dao;

import com.ch.dto.FileDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDaoImpl implements FileDao {
    private static final String namespace = "com.ch.dao.FileDao.";

    private final SqlSession session;

    public FileDaoImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public int deleteAllFiles(int bno) {
        return session.delete(namespace + "deleteAll", bno);
    }

    @Override
    public int insertFile(FileDto fileDto) throws Exception {

        return session.insert(namespace + "insertFile", fileDto);
    }

    @Override
    public int deleteFile(Integer fno) throws Exception {
        return session.delete(namespace + "deleteFile", fno);
    }

    @Override
    public List<FileDto> getFilelist(Integer bno) throws Exception {
        return session.selectList(namespace + "getFiles", bno);
    }

    @Override
    public FileDto getFile(Integer fno) throws Exception {
        return session.selectOne(namespace + "getFile", fno);
    }

//    @Override
//    public FileDto get(Integer fno) throws Exception {
//        return
//    }

    @Override
    public int updateFile(FileDto fileDto) throws Exception {
        return session.insert(namespace + "updateFile", fileDto);
    }

    @Override
    public int FileCnt(Integer bno) throws Exception {
        return session.selectOne(namespace + "FileCnt", bno);
    }


}
