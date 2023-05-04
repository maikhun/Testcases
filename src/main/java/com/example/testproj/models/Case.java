package com.example.testproj.models;

import jakarta.persistence.*;
import jdk.jfr.Name;

import java.util.Date;
import java.util.Queue;
import java.util.Set;

@Entity
@Table(name = "case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "deadline")
    private Date date;

//    @OneToMany
//    private Set<Step> steps;

    protected Case() {
    }

    public Case(String name) {
        this.name = name;
    }

    public Case(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Date getDate() {
        return date;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate() {
        this.date = date;
    }

    @Override
    public String toString() {
        return "id: " + id + "\nИмя тест-кейса: " +
                name + "\nДата: " + date;
    }

}
