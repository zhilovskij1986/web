package com.zhilovskij.controller;

import com.zhilovskij.dao.UserDAO;
import com.zhilovskij.dao.impl.JdbcTemplUserDAO;
import com.zhilovskij.model.User;
import com.zhilovskij.servise.UserServise;
import com.zhilovskij.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    //@Qualifier("jpaUserDAO")
    private UserServise userServise;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/view")
    public String view(@RequestParam(value = "name", required = false, defaultValue = "nobody") String name, Model model){
        model.addAttribute ("msg", "Hello " + name);
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute ("users", userServise.getAll ());
        return "/users";


    }
    @GetMapping("/users/new")
    public String getReturnUsers(Model model){
        model.addAttribute ("user", new User ());
        return "/return_users";
    }
    @PostMapping("/users/new")
    public String returnUsers(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validate (user, result);
        if (result.hasErrors ()){
            return "/return_users";
        }
        userServise.add (user);
        return "redirect:/users";

    }
 }




