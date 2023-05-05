package com.example.testproj.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="main_schema.case")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "deadline")
    private String deadline;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "case_id")
//    private Set<Step> steps;

    protected Case() {
    }

    public Case(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }


//    public Set<Step> getSteps() {
//        return steps;
//    }
//
//    public void setSteps(Set<Step> steps) {
//        this.steps = steps;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
