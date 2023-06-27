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

    /**
     * Нахождение списка компаний
     * @return Список всех компаний
     * */
    public List<CompanyEntity> findAllCompanies() {
        return companyRepository.findAll();
    }

    /**
     * Добавление компании
     * @param company - Добавляемая компания
     * @return Статус операции
     * */
    public Boolean addCompany(CompanyEntity company) {
        if (companyRepository.findByName(company.getName()) != null)
            return false; // Добавление существующей компании
        companyRepository.save(company);
        return true; // Добавление новой компании
    }

    /**
     * Нахождение компании по идентификатору
     * @param id - Идентификатор компании
     * @return Найденная компания или null-значение
     * */
    public Optional<CompanyEntity> findCompanyById(Long id) {
        return companyRepository.findById(id);
    }

}
