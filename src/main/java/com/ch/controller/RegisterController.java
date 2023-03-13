package com.ch.controller;

import com.ch.dto.UserDto;
import com.ch.service.UserService;
import com.ch.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    //    @Autowired
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
        return "registerForm"; // WEB-INF/views/registerForm.jsp
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute UserDto user, BindingResult result, Model m) throws Exception {

        System.out.println("result = " + result);
        System.out.println("user = " + user);

        if (!result.hasErrors()) {
            int rowCnt = userService.registerUser(user);
            if (rowCnt != 0)
                return "index";
        }
//       return "redirect:/register/add"; // 신규회원 가입화면으로 이동(redirect)
        return "registerForm";
    }

    private boolean isValid(UserDto userDto) {
        return true;
    }


}
