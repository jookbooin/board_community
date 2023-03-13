package com.ch.controller;


import com.ch.dto.CommentDto;
import com.ch.service.CommentService;
import com.ch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    //    @Autowired
    CommentService commentService;
    UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }


    @PatchMapping("/comments/{cno}")   // /ch4/comments/{cno} PATCH
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto, HttpSession session) {
        String commenterId = (String) session.getAttribute("id");
        commentDto.setCommenterId(commenterId);
        try {
            String commenter = userService.selectUser(commenterId).getNickname();
            commentDto.setCommenter(commenter);
            commentDto.setCno(cno);     //넘긴 cno
            System.out.println("commentDto = " + commentDto);

            if (commentService.modify(commentDto) != 1)  // cno 넣은 dto
                throw new Exception("modify failed.");

            return new ResponseEntity<>("mod_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("mod_ERR", HttpStatus.BAD_REQUEST);
        }
    }


    //{
    //"pcno" :0,
    //"comment" :"hiHIHI",
    //}
    @PostMapping("/comments")   // /ch4/comments?bno=1085  POST
    public ResponseEntity<String> write(@RequestBody CommentDto commentdto, Integer bno, HttpSession session) {
        String commenterId = (String) session.getAttribute("id");
        commentdto.setCommenterId(commenterId);
        try {
            String commenter = userService.selectUser(commenterId).getNickname();
            commentdto.setCommenter(commenter);
            commentdto.setBno(bno);
            System.out.println("commentdto = " + commentdto);

            if (commentService.write(commentdto) != 1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/comments/{cno}") // /comments/1?bno=189 <- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
        String commenterId = (String) session.getAttribute("id");
        System.out.println();
        System.out.println("Delete: Comment");
        System.out.println("commenterId = " + commenterId);

        try {
//            String commenter = userService.selectUser(commenterId).getNickname();
            int rowCnt = commentService.remove(cno, bno, commenterId);
            System.out.println("cno = " + cno);
            System.out.println("bno = " + bno);
            System.out.println("commenterId = " + commenterId);
            System.out.println("rowCnt = " + rowCnt);
            if (rowCnt != 1)
                throw new Exception("Del_ERR");
            return new ResponseEntity<String>("Del_OK", HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Del_err", HttpStatus.BAD_REQUEST); // 400
        }
    }

    @GetMapping("/comments") // /comments?bno=1000
    public ResponseEntity<List<CommentDto>> list(Integer bno) {
        List<CommentDto> list = null;
        System.out.println("ajax --> GET(/comments)");
        try {
            list = commentService.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getError");
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.BAD_REQUEST); // 400
        }
    }
}
