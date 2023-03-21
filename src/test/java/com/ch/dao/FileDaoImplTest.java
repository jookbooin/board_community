package com.ch.dao;

import com.ch.dto.FileDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class FileDaoImplTest {
    @Autowired
    FileDao fileDao;
    int bno = 2;
    int fno = 86;

    @Test
    public void deleteFile() throws Exception {

        fileDao.deleteAllFiles(bno);
        FileDto fileDto = new FileDto("c드라이브", "originName", "saveName", bno);
        int rowCnt = fileDao.insertFile(fileDto);
        assertTrue(rowCnt == 1);

        FileDto fileDto1 = fileDao.getFile(97);
        System.out.println("fileDto1 = " + fileDto1);
        rowCnt = fileDao.deleteFile(fileDto1.getFno());
        System.out.println("rowCnt = " + rowCnt);
        assertTrue(rowCnt == 1);


    }
}