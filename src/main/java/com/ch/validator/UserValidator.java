package com.ch.validator;

import com.ch.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz); // 검증하려는 객체가 User타입인지 확인
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        String id = user.getId();
        String pwd = user.getPwd();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");


        if (id.equals(""))
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
        else if (id == null || id.length() < 5 || id.length() > 12) {
            errors.rejectValue("id", "invalidlength", new String[]{"5", "12"}, null);
        }

        if (pwd.equals(""))
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
        else if (pwd == null || pwd.length() < 5 || pwd.length() > 12) {
            errors.rejectValue("pwd", "invalidlength", new String[]{"5", "12"}, null);
        }


    }
}
