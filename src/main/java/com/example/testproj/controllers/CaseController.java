package com.example.testproj.controllers;

import com.example.testproj.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class CaseController {

    //  Страница просмотра тест-кейсов
    @GetMapping("/create-case")
    public String createPage(Model model) {
        return "create-case";
    }

    //  Страница просмотра тест-кейсов
    @GetMapping("/view-case")
    public String viewPage(Model model) {
        User user1 = new User("Anton", "89209624296");
        ArrayList<User> arrayList = new ArrayList<>();
        arrayList.add(user1);
        model.addAllAttributes(arrayList);
        return "view-case";
    }


}
