package com.ch.controller;

import com.ch.dto.UserDto;
import com.ch.service.UserService;
import com.ch.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void toDate(WebDataBinder binder) {
        binder.setValidator(new UserValidator());// UserValidator를 WebDataBinder의 로컬(controller안에서만 쓰이는) validator로 등록
    }

    @GetMapping("/add")
    public String register() {
        logger.info("회원가입 페이지 진입");

        return "registerForm"; // WEB-INF/views/registerForm.jsp
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute UserDto userDto, BindingResult result, Model m) throws Exception {
        logger.info("회원정보 입력");
        logger.info("user={}", userDto);
        logger.info("result={}", result);

        if (!result.hasErrors()) {
            int rowCnt = userService.registerUser(userDto);
            logger.info("회원가입 성공!");
            if (rowCnt != 0)
                return "index";
        }
//       return "redirect:/register/add"; // 신규회원 가입화면으로 이동(redirect)
        return "registerForm";
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public String memberIdChkPOST(String id) throws Exception {
        logger.info("idcheck() 진입");
        int result = userService.idCheck(id);
        logger.info("idCheck_result = " + result);

        if (result != 0)
            return "fail";
        return "success";
    }

    private boolean isValid(UserDto userDto) {
        return true;
    }


}
