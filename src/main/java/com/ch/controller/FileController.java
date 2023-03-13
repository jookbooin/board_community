package com.ch.controller;//package com.ch.controller;
//
//import com.ch.service.FileService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//public class FileController {
//    // 게시글 입력처리가 되기전에 클라이언트로부터 ajax통신을 통해 첨부파일을 서버에 미리 저장해놓는다.
//
//    private final FileService fileService;
//
//    public FileController(FileService fileService) {
//        this.fileService = fileService;
//    }
//
//    @GetMapping("/files")
//    public String fileUploadForm(Model model) {
//        return "/test/files";
//    }
//
//
//    @PostMapping("/files")
//    public String fileUpload(@RequestBody MultipartFile file, RedirectAttributes attributes) {
//        System.out.println("file = " + file);
//        attributes.addFlashAttribute("filename", fileService.fileUpload(file));
//        return "redirect:/test/files";
//    }
//}
