package com.ch.interceptor;

import com.ch.dto.UserDto;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null || user.getAdmin() == 0) {    // 관리자 계정 아닌 경우
            System.out.println("관리자가 아닙니다!");
            response.sendRedirect("redirect:/");    // 메인페이지로 리다이렉트

            return false;

        }
        return true;
    }
}
