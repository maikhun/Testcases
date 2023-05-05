package com.example.testproj.services;

import com.example.testproj.models.Case;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CaseService{
    List<Case> findAllCases();
    Optional<Case> findCaseById(Integer id);
    Case saveCase(Case caseEntity);
    Case updateCase(Case caseEntity);
    void deleteCase(Integer id);

}
