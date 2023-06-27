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

    /**
     * Нахождение всех наборов выбранного проекта
     * @param project - Выбранный проект
     * @return Список наборов проекта
     * */
    public List<SetEntity> findSetsByProject(ProjectEntity project) {
        return setRepository.findAllByProject(project);
    }

    /**
     * Создание набора в проекте
     * @param project - Выбранный проект
     * @return Статус операции
     * */
    public Boolean createSet(SetEntity set, ProjectEntity project) {
        List<SetEntity> sets = setRepository.findAllByProject(project);
        if (sets.contains(set)) {
            return false;
        }
        set.setProject(project);
        sets.add(set);
        project.setSets(sets);
        setRepository.save(set);
        projectRepository.save(project);
        return true;
    }

    /**
     * Нахождение набора по идентификатору
     * @param id - Идентификатор набора
     * @return Найденный набор или null-значение
     * */
    public Optional<SetEntity> getSetById(Long id) {
        return setRepository.findById(id);
    }

}
