package com.example.johnwhisker.schoolproject;

/**
 * Created by johnw on 4/13/2016.
 */

public class Question {
    private String question, A, B, C, D;
    private int ID, answer;

    public Question() {
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setA(String a) {
        A = a;
    }

    public void setB(String b) {
        B = b;
    }

    public void setC(String c) {
        C = c;
    }

    public void setD(String d) {
        D = d;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getA() {
        return A;
    }

    public String getB() {
        return B;
    }

    public String getC() {
        return C;
    }

    public String getD() {
        return D;
    }

    public int getID() {
        return ID;
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Question: "+question+"\n"
                +"A: "+A+"\n"
                +"B: "+B+"\n"
                +"C: "+C+"\n"
                +"D: "+D+"\n"
                ;
    }
}
