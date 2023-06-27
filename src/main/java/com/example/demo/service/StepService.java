package com.example.demo.service;

import com.example.demo.entity.CaseEntity;
import com.example.demo.entity.StepEntity;
import com.example.demo.repository.CaseRepository;
import com.example.demo.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StepService {

    private final StepRepository stepRepository;
    private final CaseRepository caseRepository;


    /**
     * Добавление шага в Тест-кейс
     * @param step - Шаг, заполненный на форме
     * @param caseEntity - Тест-кейс, в который добавляется шаг
     * @return Статус операции
     * */
    public boolean createStep(StepEntity step, CaseEntity caseEntity) {
        List<StepEntity> steps = caseEntity.getSteps();
        if (steps.contains(step))
            return false;
        steps.add(step);
        caseEntity.setSteps(steps);
        caseRepository.save(caseEntity);
        step.setCaseId(caseEntity);
        stepRepository.save(step);
        return true;
    }

}
