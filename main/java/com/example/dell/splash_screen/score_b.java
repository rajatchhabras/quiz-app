package com.example.dell.splash_screen;

public class score_b {
    private int c_answer;
    private int i_answer;
    private String score;
    private double  precentage;
    private int t_questionss;
public  score_b(){}
    public score_b(int c_answer, int i_answer, String score, double precentage, int t_questionss) {
        this.c_answer = c_answer;
        this.i_answer = i_answer;
        this.score = score;
        this.precentage = precentage;
        this.t_questionss = t_questionss;
    }

    public int getC_answer() {
        return c_answer;
    }

    public void setC_answer(int c_answer) {
        this.c_answer = c_answer;
    }

    public int getI_answer() {
        return i_answer;
    }

    public void setI_answer(int i_answer) {
        this.i_answer = i_answer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public double getPrecentage() {
        return precentage;
    }

    public void setPrecentage(double precentage) {
        this.precentage = precentage;
    }

    public int getT_questionss() {
        return t_questionss;
    }

    public void setT_questionss(int t_questionss) {
        this.t_questionss = t_questionss;
    }
}
