package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserServiceImp userServiceImp;

    @Autowired
    public AdminController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping()
    public String listUsers(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("usersList", userServiceImp.allUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", userServiceImp.listRoles());

        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        List<Role> roles = userServiceImp.listRoles();
        model.addAttribute("roles", roles);
        userServiceImp.saveUser(user);
        return "redirect:/admin";
    }

//    @GetMapping("/user")
//    public String userPage(Model model, Principal principal) {
//        model.addAttribute("user", userServiceImp.loadUserByUsername(principal.getName()));
//        return "user";
//    }

    //    @PostMapping("/update")
//    public String updateUser(@ModelAttribute("user") User user, @RequestParam("roles") long[] role_id) {
//        userServiceImp.updateUser(user, role_id);
//        return "redirect:/admin";
//    }
//    @GetMapping("/update/{id}")
//    public String updateUser(@ModelAttribute("user") User user, Model model) {
//        List<Role> roles = userServiceImp.listRoles();
//        model.addAttribute("roles", roles);
//        return "admin";
//    }

    @PatchMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userServiceImp.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userServiceImp.deleteUser(id);
        return "redirect:/admin";
    }
}
