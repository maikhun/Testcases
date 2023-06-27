package com.example.demo.controller;

import com.example.demo.entity.CaseEntity;
import com.example.demo.entity.StepEntity;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companies/{idCompany}/projects/{idProject}/sets/{idSet}/cases")
public class CaseController {

    private final SetService setService;
    private final CaseService caseService;
    private final ProjectService projectService;
    private final CompanyService companyService;
    private final StepService stepService;
    private final UserService userService;

    @GetMapping
    public String getCases(@PathVariable("idCompany") Long companyId,
                           @PathVariable("idProject") Long projectId,
                           @PathVariable("idSet") Long setId, Model model, Principal principal) {
        var user = userService.findUserByEmail(principal.getName());
        var set = setService.getSetById(setId).get();
        var project = projectService.findProjectById(projectId).get();
        var company = companyService.findCompanyById(companyId).get();
        var cases = caseService.findAllCasesBySetId(setId);
        model.addAttribute("user", user);
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
        var company = companyService.findCompanyById(companyId).get();
        var project = projectService.findProjectById(projectId).get();
        var set = setService.getSetById(setId).get();

        model.addAttribute("set", set);
        model.addAttribute("project", project);
        model.addAttribute("company", company);
        model.addAttribute("case", caseEntity);
        return "case-info";
    }

    @GetMapping("/{idCase}/create-step")
    public String getCreateStepPage(@PathVariable("idCompany") Long companyId,
                                    @PathVariable("idProject") Long projectId,
                                    @PathVariable("idSet") Long setId,
                                    @PathVariable("idCase") Long caseId, Model model) {
        var caseEntity = caseService.findCaseById(caseId).get();
        var company = companyService.findCompanyById(companyId).get();
        var project = projectService.findProjectById(projectId).get();
        var set = setService.getSetById(setId).get();

        model.addAttribute("set", set);
        model.addAttribute("project", project);
        model.addAttribute("company", company);
        model.addAttribute("case", caseEntity);
        return "create-step";
    }

    @PostMapping("/{idCase}/create-step")
    public String createStep(@PathVariable("idCompany") Long companyId,
                             @PathVariable("idProject") Long projectId,
                             @PathVariable("idSet") Long setId,
                             @PathVariable("idCase") Long caseId, Model model, StepEntity step) {
        var caseEntity = caseService.findCaseById(caseId).get();
        var company = companyService.findCompanyById(companyId).get();
        var project = projectService.findProjectById(projectId).get();
        var set = setService.getSetById(setId).get();

        model.addAttribute("set", set);
        model.addAttribute("project", project);
        model.addAttribute("company", company);
        model.addAttribute("case", caseEntity);

        if (!stepService.createStep(step, caseEntity))
            return "/create-step";
        return "redirect:/companies/{idCompany}/projects/{idProject}/sets/{idSet}/cases/{idCase}";
    }

    @GetMapping("/{idCase}/update-case")
    public String getUpdateCasePage(@PathVariable("idCompany") Long companyId,
                                    @PathVariable("idProject") Long projectId,
                                    @PathVariable("idSet") Long setId,
                                    @PathVariable("idCase") Long caseId, Model model) {
        var caseEntity = caseService.findCaseById(caseId).get();
        var company = companyService.findCompanyById(companyId).get();
        var project = projectService.findProjectById(projectId).get();
        var set = setService.getSetById(setId).get();
        model.addAttribute("set", set);
        model.addAttribute("project", project);
        model.addAttribute("company", company);
        model.addAttribute("case", caseEntity);
        return "update-case";
    }

    @PostMapping("/{idCase}/update-case")
    public String updateCase(@PathVariable("idCompany") Long companyId,
                             @PathVariable("idProject") Long projectId,
                             @PathVariable("idSet") Long setId,
                             @PathVariable("idCase") Long caseId, Model model, CaseEntity caseEntity) {
        var caseFromDb = caseService.findCaseById(caseId).get();
        var company = companyService.findCompanyById(companyId).get();
        var project = projectService.findProjectById(projectId).get();
        var set = setService.getSetById(setId).get();
        model.addAttribute("set", set);
        model.addAttribute("project", project);
        model.addAttribute("company", company);
        model.addAttribute("case", caseFromDb);

        if (!caseService.updateCase(caseEntity, caseFromDb))
            return "redirect:/update-case";
        return "redirect:/companies/{idCompany}/projects/{idProject}/sets/{idSet}/cases";
    }

    @GetMapping("/{caseId}/make-test")
    public String getMakeTestPage(@PathVariable("idCompany") Long companyId,
                                  @PathVariable("idProject") Long projectId,
                                  @PathVariable("idSet") Long setId,
                                  @PathVariable("caseId") Long caseId, Model model) {
        var caseFromDb = caseService.findCaseById(caseId).get();
        var steps = caseFromDb.getSteps();
        var company = companyService.findCompanyById(companyId).get();
        var project = projectService.findProjectById(projectId).get();
        var set = setService.getSetById(setId).get();
        model.addAttribute("steps", steps);
        model.addAttribute("set", set);
        model.addAttribute("project", project);
        model.addAttribute("company", company);
        model.addAttribute("case", caseFromDb);
        return "make-test";
    }

    @PostMapping("/{caseId}/make-test")
    public String checkTest(@PathVariable("idCompany") Long companyId,
                            @PathVariable("idProject") Long projectId,
                            @PathVariable("idSet") Long setId,
                            @PathVariable("caseId") Long caseId, CaseEntity caseEntity,Model model) {
        var caseFromDb = caseService.findCaseById(caseId).get();

        return "redirect:/companies/{idCompany}/projects/{idProject}/sets/{idSet}/cases";
    }

    @PostMapping("/{caseId}/delete-case")
    public String deleteCase(@PathVariable("idCompany") Long companyId,
                             @PathVariable("idProject") Long projectId,
                             @PathVariable("idSet") Long setId,
                             @PathVariable("caseId") Long caseId, Model model) {
        var caseEntity = caseService.findCaseById(caseId).get();
        caseService.deleteCase(caseEntity);
        return "redirect:/companies/{idCompany}/projects/{idProject}/sets/{idSet}/cases";
    }

}
