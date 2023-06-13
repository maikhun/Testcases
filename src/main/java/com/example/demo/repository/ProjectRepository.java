package com.example.demo.repository;

import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    ProjectEntity findByName(String name);
    List<ProjectEntity> findProjectsByCompany(CompanyEntity company);
}
