package com.example.meancalculator;

public class GradeModel {

    private String name;
    private int value;
    private final int id;
    static int count = 0;

    public GradeModel(String name) {
        this.name = name;
        this.id = count;
        count++;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }
}
