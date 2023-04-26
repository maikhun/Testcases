package com.example.testproj.classes;

public enum PriorityType {
    A ("Высший приоритет"),
    B ("Средний приоритет"),
    C ("Малый приоритет");
    private String description;
    PriorityType(String description) {
        this.description = description;
    }

    public String getPriorityType(){
        return description;
    }

    @Override
    public String toString() {
        return "Для данной задачи:\n" +
                description;
    }

}
