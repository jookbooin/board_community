package com.ch.controller;


import com.ch.dto.UserDto;
import com.ch.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;


    @GetMapping("/login")
    public String loginForm() {

        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("로그아웃");
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }

    @PostMapping("/gnbLogout")
    @ResponseBody
    public ResponseEntity<String> gnbLogout(HttpSession session) {
        logger.info("gnb 로그아웃");
        // 1. 세션을 종료
        session.invalidate();
        // 2. 현재 페이지
        return new ResponseEntity<String>("gnblogOut", HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1. id와 pwd를 확인
        if (!loginCheck(id, pwd)) {
            // 2-1   일치하지 않으면, loginForm으로 이동
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
            return "redirect:/login/login?msg=" + msg;
        }
        // 2-2. id와 pwd가 일치하면,
        //  세션 객체를 얻어오기
        HttpSession session = request.getSession();
        //  세션 객체에 id를 저장
        UserDto loginUser = userService.selectUser(id);
        logger.info("loginUser={}", loginUser);  // 데이터 전부 가져옴
//        session.setAttribute("id", id);
        session.setAttribute("user", loginUser);
//        m.addAttribute("user", loginUser);
        if (rememberId) { // 체크박스 눌려 있으면
            //     1. 쿠키를 생성
            Cookie cookie = new Cookie("id", id);
//		       2. 응답에 저장
            response.addCookie(cookie);  // 응답헤더에 cookie 넣어줌
        } else {
            // 1. 쿠키를 삭제
            Cookie cookie = new Cookie("id", id);
            cookie.setMaxAge(0); // 쿠키를 삭제
            response.addCookie(cookie);
        }
//		       3. 홈으로 이동
        toURL = toURL == null || toURL.equals("") ? "/" : toURL;

        return "redirect:" + toURL;
    }

    private boolean loginCheck(String id, String pwd) {
        UserDto user = null;

        try {
            user = userService.selectUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return user != null && user.getPwd().equals(pwd);
    }
}