package com.example.demo.controller;

import com.example.demo.entity.CaseEntity;
import com.example.demo.entity.StepEntity;
import com.example.demo.service.CaseService;
import com.example.demo.service.CompanyService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.SetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companies/{idCompany}/projects/{idProject}/sets/{idSet}/cases")
public class CaseController {

    private final SetService setService;
    private final CaseService caseService;
    private final ProjectService projectService;
    private final CompanyService companyService;

    @GetMapping
    public String getCases(@PathVariable("idCompany") Long companyId,
                           @PathVariable("idProject") Long projectId,
                           @PathVariable("idSet") Long setId, Model model) {
        var set = setService.getSetById(setId).get();
        var project = projectService.findProjectById(projectId).get();
        var company = companyService.findCompanyById(companyId).get();
        var cases = caseService.findAllCasesBySetId(setId);
        model.addAttribute("company", company);
        model.addAttribute("cases", cases);
        model.addAttribute("project", project);
        model.addAttribute("set", set);
        return "cases";
    }

    @GetMapping("/create-case")
    public String getCreateCasePage(@PathVariable("idCompany") Long companyId,
                           @PathVariable("idProject") Long projectId,
                           @PathVariable("idSet") Long setId, Model model) {
        var set = setService.getSetById(setId).get();
        var project = projectService.findProjectById(projectId).get();
        var company = companyService.findCompanyById(companyId).get();

        model.addAttribute("company", company);
        model.addAttribute("project", project);
        model.addAttribute("set", set);
        return "create-case";
    }
    @PostMapping("/create-case")
    public String createCase(@PathVariable("idCompany") Long companyId,
                             @PathVariable("idProject") Long projectId,
                             @PathVariable("idSet") Long setId, Model model, CaseEntity caseEntity) {
        var set = setService.getSetById(setId).get();
        var project = projectService.findProjectById(projectId).get();
        var company = companyService.findCompanyById(companyId).get();

        model.addAttribute("company", company);
        model.addAttribute("project", project);
        model.addAttribute("set", set);

        if (!caseService.createCase(caseEntity, set)) {
            return "/create-case";
        }
        return "redirect:/companies/{idCompany}/projects/{idProject}/sets/{idSet}/cases";
    }

    @GetMapping("/{idCase}")
    public String getCaseInfoPage(@PathVariable("idCompany") Long companyId,
                                  @PathVariable("idProject") Long projectId,
                                  @PathVariable("idSet") Long setId,
                                  @PathVariable("idCase") Long caseId, Model model) {
        var caseEntity = caseService.findCaseById(caseId).get();
        model.addAttribute("case", caseEntity);
        return "case-info";
    }

    @GetMapping("/{idCase}/create-step")
    public String getCreateStepPage(@PathVariable("idCompany") Long companyId,
                             @PathVariable("idProject") Long projectId,
                             @PathVariable("idSet") Long setId,
                             @PathVariable("idCase") Long caseId, Model model) {
        // TODO: Дописать контроллер
        return "create-step";
    }

    @PostMapping("/{idCase}/create-step")
    public String createStep(@PathVariable("idCompany") Long companyId,
                             @PathVariable("idProject") Long projectId,
                             @PathVariable("idSet") Long setId,
                             @PathVariable("idCase") Long caseId, Model model, StepEntity step) {

        var caseEntity = caseService.findCaseById(caseId).get();

        return "redirect:/companies/{idCompany}/projects/{idProject}/sets/{idSet}/cases/{idCase}";
    }



}
