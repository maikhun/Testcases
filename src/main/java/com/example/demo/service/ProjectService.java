package com.example.demo.service;

import com.example.demo.entity.ProjectEntity;
import com.example.demo.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public boolean createProject(ProjectEntity project) {
        if (projectRepository.findByName(project.getName()) != null) return false;
        projectRepository.save(project);
        return true;
    }

    public Optional<ProjectEntity> findProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public ProjectEntity findProject(String name) {
        return projectRepository.findByName(name);
    }

    public List<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }



}
