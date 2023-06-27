package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "testCase")
@Data
public class CaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "caseName")
    private String name;

    @Column(name = "priority")
    private String priority;

    @Column(name = "seriousness")
    private String seriousness;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private SetEntity set;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "caseId")
    private List<StepEntity> steps = new ArrayList<>();
}
