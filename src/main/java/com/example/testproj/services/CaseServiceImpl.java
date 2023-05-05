package com.example.testproj.services;

import com.example.testproj.models.Case;
import com.example.testproj.repos.CaseRepo;
import com.example.testproj.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {
    private final CaseRepo caseRepository;

    public CaseServiceImpl(CaseRepo caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public List<Case> findAllCases() {
        return (List<Case>)caseRepository.findAll();
    }

    @Override
    public Optional<Case> findCaseById(Integer id) {
        return caseRepository.findById(Long.valueOf(id));
    }

    @Override
    public Case saveCase(Case caseEntity) {
        return caseRepository.save(caseEntity);
    }

    @Override
    public Case updateCase(Case caseEntity) {
        return caseRepository.save(caseEntity);
    }

    @Override
    public void deleteCase(Integer id) {
        caseRepository.deleteById(Long.valueOf(id));
    }
}
