package com.ch.util;

import com.ch.dto.FileDto;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUploadUtils {
    String rootPath = "C:\\Users\\jookbooin\\IdeaProjects\\community\\target\\ch4-1.0.0-BUILD-SNAPSHOT";

    public List<FileDto> getFileListByMurltiparts(MultipartFile[] mfiles, String path) throws IOException {
        List<FileDto> fileDtoList = new ArrayList<>();
        for (int i = 0; i < mfiles.length; i++) {
            MultipartFile multipart = mfiles[i];
            FileDto fileDto = this.getFileByMultipart(multipart, path);
            if (fileDto != null) {
                fileDtoList.add(fileDto);
            }
        }
        return fileDtoList;
    }

    public FileDto getFileByMultipart(MultipartFile mfile, String path) throws IOException {
        if (!mfile.isEmpty()) {

            String originalFileName = mfile.getOriginalFilename();
            String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf('.'));
            FileDto fileDto = new FileDto();
            fileDto.setOfname(originalFileName);
            fileDto.setSfname(saveFileName);
            fileDto.setFolder(path);

            String filePath = rootPath + File.separatorChar + fileDto.getFolder(); //C:\Users\jookbooin\IdeaProjects\board_project\target\ch4-1.0.0-BUILD-SNAPSHOT\resources/upload\230308

            FileUtils.copyInputStreamToFile(mfile.getInputStream(), new File(filePath, saveFileName));
            // 여기서 실제 파일이 저장(regist에서 실행됬다), inputStream을 file로 변환하는 메소드
            // multipart.transferTo(new File(filePath, fileName)); // 비슷한 역할
            
            return fileDto;
        } else {
            return null;
        }
    }
}