package com.ch.controller;


import com.ch.domain.PageHandler;
import com.ch.domain.SearchCondition;
import com.ch.dto.BoardDto;
import com.ch.dto.FileDto;
import com.ch.dto.UserDto;
import com.ch.service.BoardService;
import com.ch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {


    ServletContext servletContext;
    BoardService boardService;
    UserService userService;

    @Autowired
    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;


    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, @RequestParam("upfile") MultipartFile[] files, HttpServletRequest request, HttpSession session, RedirectAttributes rattr) {
//    public String write(BoardDto boardDto, Model m, HttpServletRequest request, HttpSession session, RedirectAttributes rattr) {
        String id = (String) session.getAttribute("id");
        boardDto.setId(id); // board : id

        try {
            UserDto userDto = userService.selectUser(id);
            String nickname = userDto.getNickname();
            boardDto.setNickname(nickname);  // board : nickname
            // 파일
//            resources/upload
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println("rootPath = " + rootPath);
            String uploadPath = "resources" + File.separator + "upload";

            String realPath = rootPath + uploadPath;
            System.out.println("realPath = " + realPath);
            String today = new SimpleDateFormat("yyMMdd").format(new Date()); //C:\Users\jookbooin\IdeaProjects\board_project\target\ch4-1.0.0-BUILD-SNAPSHOT\resources/upload\230308
            String saveFolder = realPath + File.separator + today;
            System.out.println("saveFolder = " + saveFolder);
            String uploadFolder = uploadPath + File.separator + today;
            System.out.println("uploadFolder = " + uploadFolder);


            File folder = new File(saveFolder);
            if (!folder.exists())
                folder.mkdirs();

            List<FileDto> fileDtolist = new ArrayList<FileDto>();
            for (MultipartFile mfile : files) {
                FileDto fileDto = new FileDto();
                String originalFileName = mfile.getOriginalFilename();
                if (!originalFileName.isEmpty()) {
                    String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf('.'));
                    fileDto.setOfname(originalFileName);
                    fileDto.setSfname(saveFileName);
                    
                    fileDto.setFolder(uploadFolder);

                    System.out.println(mfile.getOriginalFilename() + "   " + saveFileName);
                    mfile.transferTo(new File(folder, saveFileName));
                    fileDtolist.add(fileDto);
                }
            }
            System.out.println("fileDtolist.size : " + fileDtolist.size());

            boardDto.setFileDtolist(fileDtolist); // fileDtolist -> boardDto 로

            // service
            System.out.println();
            int getBno = boardService.write(boardDto);

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


    @PostMapping("/modify")
    public String modify(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {
        System.out.println();
        System.out.println("board:POST(/modify) --> board");

        // 띠용
        String id = (String) session.getAttribute("id");
        boardDto.setId(id);

        System.out.println(boardDto);
        try {

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
}