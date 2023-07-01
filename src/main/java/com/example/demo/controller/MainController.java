package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    /**
     * GET: / - Запрос на приветственную страницу
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * GET: /login - Запрос на страницу, где происходит авторизация
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * GET: /login - Запрос на страницу, где происходит регистрация
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    /**
     * POST: /login - Запрос на получение данных о регистрируемом пользователе
     */
    @PostMapping("/registration")
    public String registration(User user) {
        if (!userService.createUser(user))
            return "/registration";
        return "redirect:/login";
    }
}
