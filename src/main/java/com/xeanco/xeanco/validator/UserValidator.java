package com.xeanco.xeanco.validator;

import com.xeanco.xeanco.model.AppUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator  {

    public boolean supports(Class<?> aClass) {
        return AppUser.class.equals(aClass);
    }


    public void validate(Object object, Errors errors) {

        AppUser user = (AppUser) object;

        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "Length", "Password must be at least 6 characters");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Passwords must match");

        }

        //confirmPassword


    }
}
