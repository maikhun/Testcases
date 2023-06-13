package com.example.demo.controller;

import com.example.demo.entity.ProjectEntity;
import com.example.demo.service.CompanyService;
import com.example.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companies/{id}/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final CompanyService companyService;

    @GetMapping
    public String getProjectsPage(@PathVariable("id") Long id, Model model) {
        var company = companyService.findCompanyById(id).get();
        var projects = projectService.getAllProjects(company);
        model.addAttribute("company", company);
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/create-project")
    public String getCreateProjectPage(@PathVariable("id") Long id, Model model) {
        var company = companyService.findCompanyById(id).get();
        model.addAttribute("company", company);
        return "create-project";
    }

    @PostMapping("/create-project")
    public String createProject( @PathVariable("id") Long id, ProjectEntity project) {
        var company = companyService.findCompanyById(id).get();
        if (projectService.createProject(project, company) == true)
            return "redirect:/companies/{id}/projects";
        return "redirect:create-project";
    }

}
