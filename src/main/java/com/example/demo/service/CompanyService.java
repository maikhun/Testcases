package com.example.demo.service;

import com.example.demo.entity.CompanyEntity;
import com.example.demo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    public boolean saveCompany(CompanyEntity company) {
        if (companyRepository.findById(company.getId()) != null) {
            return false;
        }
        companyRepository.save(company);
        return true;
    }

    public List<CompanyEntity> findAllCompanies() {
        companyRepository.findAll();
    }

}
