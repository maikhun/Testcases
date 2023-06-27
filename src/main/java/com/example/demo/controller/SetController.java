package com.example.demo.controller;

import com.example.demo.entity.SetEntity;
import com.example.demo.service.CompanyService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.SetService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companies/{idCompany}/projects/{idProject}/sets")
public class SetController {

    private final ProjectService projectService;
    private final SetService setService;
    private final CompanyService companyService;
    private final UserService userService;

    @GetMapping
    public String getProjectSets(@PathVariable("idCompany") Long companyId,
                                 @PathVariable("idProject") Long projectId,
                                 Model model,
                                 Principal principal) {
        var project = projectService.findProjectById(projectId).get();
        var user = userService.findUserByEmail(principal.getName());
        var sets = setService.findSetsByProject(project);
        var company = companyService.findCompanyById(companyId).get();
        model.addAttribute("user", user);
        model.addAttribute("company", company);
        model.addAttribute("project", project);
        model.addAttribute("sets", sets);
        return "sets";
    }

    @GetMapping("/create-set")
    public String getCreateSetPage(@PathVariable("idCompany") Long companyId,
                                   @PathVariable("idProject") Long projectId, Model model) {
        var company = companyService.findCompanyById(companyId).get();
        var project = projectService.findProjectById(projectId).get();
        model.addAttribute("company", company);
        model.addAttribute("project", project);
        return "create-set";
    }

    @PostMapping("/create-set")
    public String createSet(@PathVariable("idCompany") Long companyId,
                            @PathVariable("idProject") Long projectId, SetEntity set) {
        var project = projectService.findProjectById(projectId).get();
        if (!setService.createSet(set, project))
            return "/create-set";
        return "redirect:/companies/{idCompany}/projects/{idProject}/sets";
    }


}
