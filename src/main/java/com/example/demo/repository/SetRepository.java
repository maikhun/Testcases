package com.example.demo.repository;

import com.example.demo.entity.CaseEntity;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.entity.SetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SetRepository extends JpaRepository<SetEntity, Long> {

    List<SetEntity> findAllByProject(ProjectEntity project);
    SetEntity findByName(String name);

}
