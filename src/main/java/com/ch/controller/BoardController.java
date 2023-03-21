package com.ch.controller;


import com.ch.domain.PageHandler;
import com.ch.domain.SearchCondition;
import com.ch.dto.BoardDto;
import com.ch.dto.FileDto;
import com.ch.dto.UserDto;
import com.ch.service.BoardService;
import com.ch.service.FileService;
import com.ch.service.UserService;
import com.ch.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    BoardService boardService;
    UserService userService;
    FileService fileService;
    FileUploadUtils fileUploadUtils;  // 파일 업로드

    @Autowired
    public BoardController(BoardService boardService, UserService userService, FileUploadUtils fileUploadUtils, FileService fileService) {
        this.boardService = boardService;
        this.userService = userService;
        this.fileUploadUtils = fileUploadUtils;
        this.fileService = fileService;

    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, BindingResult error, Model m,
                        @RequestParam("boFiles") MultipartFile[] files, HttpSession session, RedirectAttributes rattr) throws Exception {
        setIdName(boardDto, session);
        try {

            if (files != null) {
                List<FileDto> fileDtolist = fileUploadUtils.getFileListByMurltiparts(files, uploadFolder());
                boardDto.setFileDtolist(fileDtolist);
            }

            
            // service
            System.out.println();
            boardService.write(boardDto);  // 여기서 파일 업로드

            System.out.println("<after write board>");
            System.out.println("boardDto = " + boardDto);
            rattr.addFlashAttribute("msg", "WRT_OK");
            System.out.println("board:POST(/write) --> /board/list");
            System.out.println();
            return "redirect:/board/list";

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "WRT_ERR");
            m.addAttribute(boardDto);
            return "boardList";
        }
    }


    @GetMapping("/write")   // 글쓰기 버튼 --> 게시판 글쓰기
    public String write(Model m) {

        m.addAttribute("mode", "new");

        System.out.println();
        System.out.println("boardlist --> board:GET(/write)");
        System.out.println("mode = new");
        return "board";
    }

    @PostMapping("/remodify")
    public String modify(BoardDto boardDto, Model m, HttpSession session) throws Exception {
        setIdName(boardDto, session);
        boardDto.setFileDtolist(fileService.getBoardFiles(boardDto.getBno()));

        System.out.println("board:POST(/remodify) --> board");
        m.addAttribute("mode", "modify");
        m.addAttribute(boardDto);
        System.out.println("boardDto = " + boardDto);

        return "board";
    }


    @PostMapping("/modify")
    public String modify(BoardDto boardDto, BindingResult error, Model m,
                         @RequestParam("boFiles") MultipartFile[] files,
                         HttpSession session, RedirectAttributes rattr) throws Exception {

        if (error.hasErrors()) {
            return "forward:/board/remodify";
        }


        System.out.println();
        System.out.println("board:POST(/modify) --> board");

        try {
            setIdName(boardDto, session);
            System.out.println("boardDto = " + boardDto);

            if (files != null) {
                List<FileDto> fileDtolist = fileUploadUtils.getFileListByMurltiparts(files, uploadFolder());
                boardDto.setFileDtolist(fileDtolist);
            }

            int rowCnt = boardService.modify(boardDto);

            if (rowCnt != 1)
                throw new Exception("mod Err");

            rattr.addFlashAttribute("msg", "MOD_OK");

            return "board";

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "MOD_ERR");
            m.addAttribute(boardDto);
            return "board";
        }
    }


    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute(boardDto); // "boardDto"
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("board:GET(/read) --> board");
        return "board";
    }


    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        System.out.println();
        System.out.println("board --> POST(/remove)");

        String writer = (String) session.getAttribute("id");

        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
            int rowCnt = boardService.remove(bno, writer);

            if (rowCnt != 1)
                throw new Exception("remove Err");

            rattr.addFlashAttribute("msg", "DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }

        // 모델에 담으면 redirect 할 때 쿼리 스트링에 ?page=i&pageSize=i 으로 붙음
        System.out.println("board:POST(/remove) --> redirect:/board/list");
        return "redirect:/board/list";
    }


    @GetMapping("/list")
    public String list(@ModelAttribute SearchCondition sc, Model m, HttpServletRequest request) {

        if (!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        System.out.println();
        System.out.println(" --> board:GET(/list)");

        try {
            // 페이지 개수 전달
            int totalCnt = boardService.getSearchResultCnt(sc);
            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getsearchResultPage(sc);
//            System.out.println("list = " + list);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            //확인용
            System.out.println("totalCnt = " + totalCnt);
            System.out.println("beginPage=" + pageHandler.getBeginPage());
            System.out.println("endPage=" + pageHandler.getEndPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("board --> boardList");
        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) { // 요청 헤더
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        System.out.println("sessionId : " + session.getAttribute("id"));

        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id") != null;
    }

    private void setIdName(BoardDto boardDto, HttpSession session) throws Exception {
        String id = (String) session.getAttribute("id");
        boardDto.setId(id); // board : id

        try {
            UserDto userDto = userService.selectUser(id);
            String nickname = userDto.getNickname();
            boardDto.setNickname(nickname);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public String uploadFolder() {
        String uploadPath = "resources" + File.separator + "upload";
        String today = new SimpleDateFormat("yyMMdd").format(new Date()); //C:\Users\jookbooin\IdeaProjects\board_project\target\ch4-1.0.0-BUILD-SNAPSHOT\resources/upload\230308
        String uploadFolder = uploadPath + File.separator + today;  // resources/upload/today
        return uploadFolder;
    }
}