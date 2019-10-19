package com.zhilovskij.validate;

import com.zhilovskij.dao.UserDAO;
import com.zhilovskij.dao.impl.JdbcTemplUserDAO;
import com.zhilovskij.model.User;
import com.zhilovskij.servise.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    //@Qualifier("jpaUserDAO")
    private UserServise userServise;


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals (aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userServise.getOne (user.getEmail ()) != null) {
            errors.rejectValue ("email", "", "This email is already in use");
        }
    }
}
