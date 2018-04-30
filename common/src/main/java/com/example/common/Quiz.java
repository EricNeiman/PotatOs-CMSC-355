package com.example.common;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date; // for close time

public class Quiz {
    private String quizName; // quiz name text
    private String password; // quiz password
    private ArrayList<Question> questions; // question storage
    private Date openTime; // date that the quiz closes
    private Date closeTime; // date that the quiz closes
    private Double timer; // quiz timer (uses fractional hours)
    private User owner; // quiz owner object
    private Boolean submitted; // true if quiz was submitted on time
    private int id; //database id
    private Boolean submittedOnTime; // true if quiz was submitted on time
    private int classId;
    private int studentId;
    private boolean isTaken;

    public Quiz(String quizName, String password, ArrayList<Question> questions, Date openTime, Date closeTime, Double timer, User owner, boolean submitted) {
        this.quizName = quizName;
        this.password = password;
        this.checkCollections();
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.timer = timer;
        this.owner = owner;
        this.submitted = submitted;
        this.submittedOnTime = false;
    } // basic constructor

    public Quiz() {
        this.quizName = "";
        this.password = "";
        this.checkCollections();
        this.openTime = null;
        this.closeTime = null;
        this.timer = 0.0;
        this.owner = null;
        this.submitted = false;
        this.submittedOnTime = false;
    } // default constructor

    private void checkCollections() {
        if (this.questions == null) {
            this.questions = new ArrayList<>();
        }
    }

    public boolean isOpen() {
        if (closeTime.after(new Date()) && openTime.before(new Date())) { // current time is between openTime and closeTime
            return true;
        } else {
            return false;
        }
    }

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

    public void appendQuestions(Question q) {
        this.questions.add(q);
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
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
        return Double.valueOf(this.getPointsEarned() / this.getPointsPossible());
    } // value of grade is calculated based off points earned and total points

    public int getPointsEarned() {
        int pointsEarned = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getCorrect()) {
                pointsEarned += questions.get(i).getPointValue();
            }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Quiz fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Quiz.class);
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}