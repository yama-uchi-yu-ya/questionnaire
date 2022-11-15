package com.example.questionnaire;

public class ViewAnswer {

    public int answer_id;
    public int meat_id;
    public String idol_name;

    public int getAnswer_id() {
        return this.answer_id;
    }
    public int getMeat_id() {
        return this.meat_id;
    }
    public String getIdol_name() {
        return this.idol_name;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }
    public void setMeat_id(int meat_id) {
        this.meat_id = meat_id;
    }
    public void setIdol_name(String idol_name) {
        this.idol_name = idol_name;
    }

    public ViewAnswer() {
    }

    public ViewAnswer(int answer_id, int meat_id, String idol_name, int vegetable_id) {
        this.answer_id = answer_id;
        this.meat_id = meat_id;
        this.idol_name = idol_name;
    }
}
