package com.example.testproj.controllers;

import com.example.testproj.models.Case;
import com.example.testproj.services.CaseServiceImpl;
import org.springframework.stereotype.Controller;
import java.util.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    public final CaseServiceImpl caseService;
    public MainController(CaseServiceImpl caseService) {
        this.caseService = caseService;
    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("description", "Создание/просмотр тест-кейсов");
        model.addAttribute("title", "Тест-кейсы");
        return "index";
    }

    @GetMapping("/create-case")
    public String createPage(Model model) {
        return "create-case";
    }

    @GetMapping("/view-case")
    public String viewPage(Model model) {
        var list = caseService.findAllCases();
        model.addAttribute("cases", list);
        return "view-case";
    }

}
