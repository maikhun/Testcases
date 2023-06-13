package com.example.demo.service;

import com.example.demo.entity.CaseEntity;
import com.example.demo.entity.SetEntity;
import com.example.demo.repository.CaseRepository;
import com.example.demo.repository.SetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;
    private final SetRepository setRepository;

    public List<CaseEntity> findAllCasesBySetId(Long id) {
        return caseRepository.findBySet_Id(id);
    }

    // Создание тест-кейса
    public boolean createCase(CaseEntity caseEntity, SetEntity set) {
        List<CaseEntity> setCases = set.getCases();
        if (setCases.contains(caseEntity)) {
            return false;
        }
        setCases.add(caseEntity);
        set.setCases(setCases);
        setRepository.save(set);
        caseEntity.setSet(set);
        caseRepository.save(caseEntity);
        return true;
    }

    public Optional<CaseEntity> findCaseById(Long id) {
        return caseRepository.findById(id);
    }

}
