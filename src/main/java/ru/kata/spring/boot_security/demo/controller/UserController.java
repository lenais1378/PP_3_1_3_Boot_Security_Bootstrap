package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserServiceImp userServiceImp;
    private User user;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    //    @GetMapping()
//    public String userPage(Model model) {
//        model.addAttribute("user", userServiceImp.getCurrentUser());
//        return "user";
//    }
    @GetMapping()
    public String userPage(Model model) {
        model.addAttribute("user", userServiceImp.getCurrentUser());
        return "user";
    }
}