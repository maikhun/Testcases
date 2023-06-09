package com.example.demo.controller;

import com.example.demo.entity.ProjectEntity;
import com.example.demo.entity.SetEntity;
import com.example.demo.service.CaseService;
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
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    private final SetService setService;

    private final CaseService caseService;

    @GetMapping
    public String projects(Model model) {
        var projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/create-project")
    public String createProjectPage() {
        return "create-project";
    }

    @PostMapping("/create-project")
    public String createProject(ProjectEntity project) {
        projectService.createProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/sets")
    public String currentProject(@PathVariable Long id, Model model) {
        var project = projectService.findProjectById(id).get();
        var sets = setService.findAllSetsByProjectId(project.getId());

        model.addAttribute("project", project);
        model.addAttribute("sets", sets);
        return "sets";
    }

    @GetMapping("/{id}/sets/create-set")
    public String createPage(@PathVariable Long id, Model model) {
        var project = projectService.findProjectById(id).get();
        model.addAttribute("project", project);
        return "create-set";
    }

    @PostMapping("/{id}/sets/create-set")
    public String createSet(@PathVariable Long id, SetEntity set, Model model) {

        if (set.getProject().getId() != id) return "create-set";

        setService.createSet(set, id);
        return "redirect:/projects/{id}/sets";
    }

    @GetMapping("/{project_id}/sets/{set_id}")
    public String currentProjectSet(@PathVariable(name = "project_id") Long projectId,
                                    @PathVariable(name = "set_id") Long setId, Model model) {
        var project = projectService.findProjectById(projectId).get();
        var set = setService.findSetById(setId).get();
        var cases = caseService.findAllCasesBySetId(set.getId());
        model.addAttribute("project", project);
        model.addAttribute("set", set);
        model.addAttribute("cases", cases);
        return "cases";
    }

    @GetMapping("/projects/{projectId}/sets/{setId}/create-case")
    public String getCreateCase(@PathVariable Long projectId, @PathVariable Long setId) {
        var project = projectService.findProjectById(projectId).get();
        var set = setService.findSetById(setId).get();
        return "create-case";
    }


}
