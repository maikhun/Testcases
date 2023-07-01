package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Шаг
 * */
@Entity
@Table
@Data
public class StepEntity {

    // Идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Наименование
    @Column(name = "name")
    private String name;

    // Фактический результат
    @Column(name = "factRes")
    private String factRes;

    // Ожидаемый результат
    @Column(name = "waitRes")
    private String waitRes;

    // Идентификатор
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "caseId")
    private CaseEntity caseId;

    public String defaultResult(String factRes) {
        return (factRes == null) ? "" : factRes;
    }
}
