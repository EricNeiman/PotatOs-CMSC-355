package com.example.common;

import java.util.Date; // for Close time

public class Quiz {
    private String quizName; // quiz name text
    private String password; // quiz password
    private Question[] questions; // question storage TODO switch to array list
    private Date closeTime; // date that quiz close
    private Double timer; // quiz timer (uses fractional hours)
    private User owner; // quiz owner object
    private int pointsEarned; // points for correct answers TODO implement method to tally up earned points based on correct answers
    private int pointsPossible; // points possible TODO implement method to tally up points based on questions' individual values
    private Boolean submitted; // true if quiz was submitted on time

    private int quizId; //database id
    public Quiz(String quizName, String password, Question[] questions, Date closeTime, Double timer, User owner, int pointsEarned, int pointsPossible, boolean submitted) {
        this.quizName = quizName;
        this.password = password;
        this.questions = questions;
        this.closeTime = closeTime;
        this.timer = timer;
        this.owner = owner;
        this.pointsEarned = pointsEarned;
        this.pointsPossible = pointsPossible;
        this.submitted = submitted;
    } // basic constructor

    public Quiz() {
        this.quizName = "";
        this.password = "";
        this.questions = null;
        this.closeTime = null;
        this.timer = 0.0;
        this.owner = null;
        this.pointsEarned = 0;
        this.pointsPossible = 0;
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

    public Question[] getQuestions() {
        return questions;
    }
    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public Date getCloseTime() {
        return closeTime;
    }
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
        return Double.valueOf(pointsEarned/pointsPossible);
    } // value of grade is calculated based off points earned and total points TODO possibly round to 2 decimal points

    public int getPointsEarned() {
        return pointsEarned;
    }
    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }
    public void setPointsPossible(int pointsPossible) {
        this.pointsPossible = pointsPossible;
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