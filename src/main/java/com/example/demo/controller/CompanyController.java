package com.example.demo.controller;

import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.entity.User;
import com.example.demo.service.CompanyService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final UserService userService;

    /**
     * GET: /companies - Запрос на страницу, где находится список всех компаний
     */
    @RequestMapping("/companies")
    public String companies(Principal principal, Model model) {
        var user = userService.findUserByEmail(principal.getName());
        var companies = companyService.findAllCompanies();
        model.addAttribute("companies", companies);
        model.addAttribute("user", user);
        return "companies";
    }

    /**
     * GET: /companies/create-company - Запрос на страницу, где происходит добавление новой компании
     */
    @GetMapping("/companies/create-company")
    public String createCompany() {
        return "create-company";
    }

    /**
     * POST: /companies/create-company - Запрос на получение данных о добавляющейся компании
     */
    @PostMapping("/companies/create-company")
    public String createCompany(CompanyEntity company) {
        if (!companyService.addCompany(company))
            return "redirect:/create-company";
        return "redirect:/companies";
    }
}
