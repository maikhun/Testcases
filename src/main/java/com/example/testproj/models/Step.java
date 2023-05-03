package com.example.testproj.models;

import javax.persistence.*;

@Entity
@Table(name="step")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="id")
    public Case caseId;

    public Step() {

    }

    public Step(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
