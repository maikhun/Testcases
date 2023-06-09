package com.example.demo.service;

import com.example.demo.entity.SetEntity;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.SetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class SetService {

    private final SetRepository setRepository;
    private final ProjectRepository projectRepository;

    public List<SetEntity> findAllSetsByProjectId(Long id) {
        return setRepository.findByProjectId(id);
    }

    public Optional<SetEntity> findSetById(Long id) {
        return setRepository.findById(id);
    }

    public boolean createSet(SetEntity set, Long id) {
        if (setRepository.findByName(set.getName()) != null) return false;
        var project = projectRepository.findById(id).get();
        List<SetEntity> setsOfProject = project.getSets();
        set.setProject(project);
        setRepository.save(set);
        return true;
    }

}
