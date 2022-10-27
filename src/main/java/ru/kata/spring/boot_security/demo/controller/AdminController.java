package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String listUsers(ModelMap model) {
        model.addAttribute("usersList", userService.allUsers());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping(value = "/admin")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        List<Role> roles = userService.listRoles();
        model.addAttribute("roles", roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "useradmin";
    }

    @GetMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        List<Role> roles = userService.listRoles();
        model.addAttribute("roles", roles);
        return "useradmin";
    }
    @PostMapping("/user-update/{id}")
    public String updateUser(Long id, @ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/delete/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
