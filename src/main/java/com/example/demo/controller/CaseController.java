package com.example.demo.controller;

import com.example.demo.entity.CaseEntity;
import com.example.demo.entity.StepEntity;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * GET: /cases - Запрос на страницу, где располагаются все тест-кейсы
     */
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

    /**
     * GET: /create-case - Запрос на страницу, где происходит создание тест-кейса
     */
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

    /**
     * POST: /create-case - Запрос на прием данных со страницы /create-case
     */
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

    /**
     * GET: /{idCase} - Запрос на страницу, где находятся данные о тест-кейсе с id = idCase
     */
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

    /**
     * GET: /{idCase}/create-step - Запрос на страницу, где происходит создание шага тест-кейса с id = idCase
     */
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

    /**
     * POST: /{idCase}/create-step - Запрос на прием данных со страницы /{idCase}/create-step
     */
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

    /**
     * GET: /{idCase}/update-step - Запрос на страницу, где происходит изменение информации тест-кейса с id = idCase
     */
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

    /**
     * POST: /{idCase}/update-case - Запрос на прием данных со страницы /{idCase}/update-case
     */
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

    /**
     * GET: /{caseId}/{stepId}/make-test - Запрос на страницу, где проводится тестирование шага
     */
    @GetMapping("/{caseId}/{stepId}/make-test")
    public String getMakeTestPage(@PathVariable("idCompany") Long companyId,
                                  @PathVariable("idProject") Long projectId,
                                  @PathVariable("idSet") Long setId,
                                  @PathVariable("caseId") Long caseId,
                                  @PathVariable("stepId") Long stepId,
                                  Model model) {
        var caseFromDb = caseService.findCaseById(caseId).get();
        var company = companyService.findCompanyById(companyId).get();
        var project = projectService.findProjectById(projectId).get();
        var step = stepService.findStep(stepId).get();
        var set = setService.getSetById(setId).get();
        model.addAttribute("step", step);
        model.addAttribute("set", set);
        model.addAttribute("project", project);
        model.addAttribute("company", company);
        model.addAttribute("case", caseFromDb);
        return "make-test";
    }

    /**
     * POST: /{caseId}/{stepId}/make-test - Запрос на прием данных со страницы /{caseId}/{stepId}/make-test
     */
    @PostMapping("/{caseId}/{stepId}/make-test")
    public String checkTest(@PathVariable("idCompany") Long companyId,
                            @PathVariable("idProject") Long projectId,
                            @PathVariable("idSet") Long setId,
                            @PathVariable("caseId") Long caseId,
                            @PathVariable("stepId") Long stepId,
                            @RequestParam("factRes") String factRes, Model model) {
        var caseFromDb = caseService.findCaseById(caseId).get();
        var stepFromDb = stepService.findStep(stepId).get();
        stepService.makeTest(factRes, stepFromDb);
        return "redirect:/companies/{idCompany}/projects/{idProject}/sets/{idSet}/cases/{caseId}";
    }

    /**
     * POST: /{caseId}/delete-case - Запрос на удаление тест-кейса с id = caseId
     */
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
