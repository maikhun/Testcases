package com.example.demo.controller;

import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public String companies(Model model) {
        var companies = companyService.findAllCompanies();
        model.addAttribute("companies", companies);
        return "companies";
    }

    @GetMapping("/companies/create-project")
    public String createCompany() {
        return "create-company";
    }

    @PostMapping("/companies/create-company")
    public String createCompany(CompanyEntity company) {
        companyService.saveCompany(company);
        return "redirect:/companies";
    }

}
