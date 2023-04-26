package com.example.testproj.controllers;

import org.springframework.stereotype.Controller;
import java.util.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("description", "Создание/просмотр тест-кейсов");
        model.addAttribute("title", "Тест-кейсы");
        return "index";
    }

}
