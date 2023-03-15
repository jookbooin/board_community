package com.ch.controller;//package com.ch.controller;

import com.ch.dto.FileDto;
import com.ch.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class FileController {

    private final FileService fileService;
    String uploadPath = "C:\\Users\\jookbooin\\IdeaProjects\\community\\target\\ch4-1.0.0-BUILD-SNAPSHOT\\";

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/files")
    public String fileUploadForm(Model model) {
        return "/test/files";
    }

    @RequestMapping("/attach/download/{fno}")
    public void process(@PathVariable int fno, HttpServletResponse response) throws Exception {
        try {
            FileDto fileDto = fileService.getFile(fno);
            String originalName = new String(fileDto.getOfname().getBytes("utf-8"), "iso-8859-1");
            String filePath = uploadPath + File.separatorChar + fileDto.getFolder();
            String fileName = fileDto.getSfname();

            File f = new File(filePath, fileName);
            // 다운로드를 위한 헤더
            response.setHeader("Content-Type", "application/octet-stream;");  // binary data (파일 8bit)
            response.setHeader("Content-Disposition", "attachment;filename=\"" + originalName + "\";"); // 다운로드 시 파일 다운로드 대화상자 뜨도록 하는 속성
            response.setHeader("Content-Transfer-Encoding", "binary;"); // 디코더가 메시지의  body를 원래의 포맷으로 바꾸기 위해 사용하는 디코딩 방식
            FileUtils.copyFile(f, response.getOutputStream());
            response.getOutputStream().close();
        } catch (IOException e) {
            printMessage(response, "해당 첨부파일이 존재하지 않습니다.");
            response.reset();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
        }
    }

    private void printMessage(HttpServletResponse response, String msg) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        //target이 지정되지 않은 경우 history.back() 으로 처리
        out.println("<script type='text/javascript'>");
        out.println(" alert('" + msg + "');");
        out.println(" self.close();");
        out.println("</script>");
        out.println("<h4>첨부파일 문제 " + msg + "</h4>");
        out.println("<button onclick='self.close()'>닫기</button>");
    }

}
