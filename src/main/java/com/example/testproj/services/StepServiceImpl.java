package com.example.testproj.services;


import com.example.testproj.models.Case;
import com.example.testproj.models.Step;
import com.example.testproj.repos.CaseRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class StepServiceImpl implements StepService {
    @Autowired
    private final CaseRepo caseRepository;

    public StepServiceImpl(CaseRepo caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public Iterable<Case> findAllStepsByCase(Case caseEntity) {
        return caseRepository.findAll();
    }

    @Override
    public Step saveStep(Step step) {
        return null;
    }

    @Override
    public void deleteAllSteps(Case caseEntity) {

    }

    @Override
    public void deleteStepFromCase(Case caseEntity, Integer id) {

    }
}
