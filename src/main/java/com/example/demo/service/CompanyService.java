package com.example.demo.service;

import com.example.demo.entity.CompanyEntity;
import com.example.demo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    // Список всех компаний
    public List<CompanyEntity> findAllCompanies() {
        return companyRepository.findAll();
    }

    // Добавление компании
    public Boolean addCompany(CompanyEntity company) {
        if (companyRepository.findByName(company.getName()) != null)
            return false; // Добавление существующей компании
        companyRepository.save(company);
        return true; // Добавление новой компании
    }

    // Компания по идентификатору
    public Optional<CompanyEntity> findCompanyById(Long id) {
        return companyRepository.findById(id);
    }

}
