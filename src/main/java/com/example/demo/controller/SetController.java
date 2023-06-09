package com.example.demo.controller;

import com.example.demo.entity.SetEntity;
import com.example.demo.service.ProjectService;
import com.example.demo.service.SetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/project/{id}/sets")
public class SetController {

    private final ProjectService projectService;

    private final SetService setService;

    @GetMapping
    public String getAllSets() {
        return "sets";
    }


}
