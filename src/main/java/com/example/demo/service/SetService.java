package com.example.demo.service;

import com.example.demo.entity.ProjectEntity;
import com.example.demo.entity.SetEntity;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.SetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SetService {

    private final SetRepository setRepository;
    private final ProjectRepository projectRepository;

    // Список наборов проекта
    public List<SetEntity> findSetsByProject(ProjectEntity project) {
        return setRepository.findAllByProject(project);
    }

    // Создание набора проекта
    public Boolean createSet(SetEntity set, ProjectEntity project) {
        List<SetEntity> sets = setRepository.findAllByProject(project);
        if (sets.contains(set)) {
            return false;
        }
        sets.add(set);
        project.setSets(sets);
        setRepository.save(set);
        projectRepository.save(project);
        return true;
    }

    public Optional<SetEntity> getSetById(Long id) {
        return setRepository.findById(id);
    }

}
