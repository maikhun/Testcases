package com.example.demo.service;

import com.example.demo.entity.CaseEntity;
import com.example.demo.entity.SetEntity;
import com.example.demo.repository.CaseRepository;
import com.example.demo.repository.SetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;
    private final SetRepository setRepository;

    /**
     * Нахождение всех Тест-кейсов по выбранному пользователем набору
     * @param id Идентификатор набора
     * @return Список Тест-кейсов
     * */
    public List<CaseEntity> findAllCasesBySetId(Long id) {
        return caseRepository.findBySet_Id(id);
    }

    /**
     * Создание Тест-кейса
     * @param caseEntity Тест-кейс из формы
     * @param set Набор, который в данный момент выбран пользователем
     * @return Статус операции
     * */
    public boolean createCase(CaseEntity caseEntity, SetEntity set) {
        List<CaseEntity> setCases = set.getCases();
        if (setCases.contains(caseEntity)) {
            return false;
        }
        String status = "Активный";
        caseEntity.setStatus(status);
        setCases.add(caseEntity);
        set.setCases(setCases);
        setRepository.save(set);
        caseEntity.setSet(set);
        caseRepository.save(caseEntity);
        return true;
    }

    /**
     * Изменение Тест-кейса
     * @param caseFromDb Тест-кейс из БД
     * @param caseFromForm Тест-кейс из формы
     * @return Статус операции
     * */
    public boolean updateCase(CaseEntity caseFromForm, CaseEntity caseFromDb) {
        if (caseRepository.findById(caseFromDb.getId()).get() == null)
            return false;
        if (!caseFromForm.getName().isBlank())
            caseFromDb.setName(caseFromForm.getName());
        if (!caseFromForm.getPriority().isBlank())
            caseFromDb.setPriority(caseFromForm.getPriority());
        if (!caseFromForm.getSeriousness().isBlank())
            caseFromDb.setSeriousness(caseFromForm.getSeriousness());
        caseRepository.save(caseFromDb);
        return true;
    }

    /**
     * Удаление Тест-кейса
     * @param caseEntity Выбранный пользователем Тест-кейс
     * */
    public void deleteCase(CaseEntity caseEntity) {
        SetEntity set = caseEntity.getSet();
        List<CaseEntity> setCases = set.getCases();
        setCases.remove(caseEntity);
        set.setCases(setCases);
        setRepository.save(set);
        caseRepository.delete(caseEntity);
    }

    /**
     * Нахождение Тест-кейса по идентификатору
     * @param id Идентификатор тест-кейса
     * @return Найденный Тест-кейс или null-значение
     * */
    public Optional<CaseEntity> findCaseById(Long id) {
        return caseRepository.findById(id);
    }

}
