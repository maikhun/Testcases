package com.example.testproj.controllers;

import com.example.testproj.repos.CaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CaseController {

    @Autowired
    private CaseRepo caseRepository;

    //  Страница просмотра тест-кейсов
    @GetMapping("/create-case")
    public String createPage(Model model) {
        return "create-case";
    }



    //  Страница просмотра тест-кейсов
    @GetMapping("/view-case")
    public String viewPage(Model model) {
        model.addAttribute("case", caseRepository.findAll());
        return "view-case";
    }

//    @PostMapping("/create-case/{id}")
//    public String postRequest(@RequestParam String name,
//                              @RequestParam String priority,
//                              @RequestParam Date date,
//                              @RequestParam Collection steps,
//                              Model model) {
//        model.addAttribute("message", "Тест-кейс создан!");
//
//    }


}
