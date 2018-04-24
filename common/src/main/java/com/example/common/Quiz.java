package com.example.common;

import java.util.ArrayList;
import java.util.Date; // for Close time

public class Quiz {
    private String quizName; // quiz name text
    private String password; // quiz password
    private ArrayList<Question> questions; // question storage
    private Date closeTime; // date that quiz close
    private Double timer; // quiz timer (uses fractional hours)
    private User owner; // quiz owner object
    private Boolean submitted; // true if quiz was submitted on time

    private int quizId; //database id
    public Quiz(String quizName, String password, ArrayList<Question> questions, Date closeTime, Double timer, User owner, boolean submitted) {
        this.quizName = quizName;
        this.password = password;
        this.questions = questions;
        this.closeTime = closeTime;
        this.timer = timer;
        this.owner = owner;
        this.submitted = submitted;
    } // basic constructor

    public Quiz() {
        this.quizName = "";
        this.password = "";
        this.questions = null;
        this.closeTime = null;
        this.timer = 0.0;
        this.owner = null;
        this.submitted = false;
    } // default constructor

    // getters and setters for each variable
    public String getQuizName() {
        return quizName;
    }
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Date getCloseTime() {
        return closeTime;
    } // TODO kick out user when close time passes
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Double getTimer() {
        return timer;
    }
    public void setTimer(Double timer) {
        this.timer = timer;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Double getGrade() {
        return Double.valueOf(this.getPointsEarned()/this.getPointsPossible());
    } // value of grade is calculated based off points earned and total points

    public int getPointsEarned() {
        int pointsEarned = 0;
        for (int i = 0; i < questions.size(); i++) {
           if (questions.get(i).getCorrect()) {pointsEarned += questions.get(i).getPointValue();}
        }
        return pointsEarned;
    }

    public int getPointsPossible() {
        int pointsPossible = 0;
        for (int i = 0; i < questions.size(); i++) {
           pointsPossible += questions.get(i).getPointValue();
        }
        return pointsPossible;
    }

    public Boolean getSubmitted() {
        return submitted;
    }
    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}