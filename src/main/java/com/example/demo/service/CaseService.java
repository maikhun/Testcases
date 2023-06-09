package com.example.demo.service;

import com.example.demo.entity.CaseEntity;
import com.example.demo.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;

    public List<CaseEntity> findAllCasesBySetId(Long id) {
        return caseRepository.findBySet_Id(id);
    }

}
