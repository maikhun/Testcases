package com.example.testproj.services;

import com.example.testproj.models.Case;
import com.example.testproj.models.Step;
import org.springframework.stereotype.Service;

@Service
public interface StepService {
    Iterable<Case> findAllStepsByCase(Case caseEntity);
    Step saveStep(Step step);
    void deleteAllSteps(Case caseEntity);
    void deleteStepFromCase(Case caseEntity, Integer id);
}
