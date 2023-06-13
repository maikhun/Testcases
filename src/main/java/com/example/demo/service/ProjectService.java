package com.example.demo.service;

import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.repository.CompanyRepository;
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
    private final CompanyRepository companyRepository;

    // Создание проекта
    public boolean createProject(ProjectEntity project, CompanyEntity company) {
        List<ProjectEntity> companyProjects = getAllProjects(company);
        if (companyProjects.contains(project) != false)
            return false;
        companyProjects.add(project);
        company.setProjects(companyProjects);
        project.setCompany(company);
        projectRepository.save(project);
        companyRepository.save(company);
        return true;
    }

    // Все проекты определенной компании
    public List<ProjectEntity> getAllProjects(CompanyEntity company) {
        return projectRepository.findProjectsByCompany(company);
    }

    // Проект по идентификатору
    public Optional<ProjectEntity> findProjectById(Long id) {
        return projectRepository.findById(id);
    }

}
