package com.example.questionnaire;

public class Meat {
    private int meat_id;
    private String name;
    public void setMeatId(int meat_id) {
        this.meat_id = meat_id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Meat() {
    }
    public Meat(int meat_id, String name) {
        this.meat_id = meat_id;
        this.name = name;
    }
}
