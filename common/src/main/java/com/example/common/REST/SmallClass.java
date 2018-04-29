package com.example.common.REST;

import com.example.common.Class;
import com.example.common.Quiz;
import com.example.common.User;

import java.util.ArrayList;


public class SmallClass {
    //this is what is submitted to the server

    private int ownerId; // class owner user object
    private String className; // class name text
    private String classCode; // class code text
    private int classID; //the class ID is assigned by the database and should be used in api calls

    private ArrayList<Integer> enrolledUsers;
    private ArrayList<Integer> quizzes;

    public SmallClass() {
        this.quizzes = new ArrayList<>();
        this.enrolledUsers = new ArrayList<>();
    }

    public SmallClass(Class cls) {
        this.ownerId = cls.getOwnerId();
        this.className = cls.getClassName();
        this.classCode = cls.getClassCode();
        this.classID = cls.getClassID();
        this.quizzes = new ArrayList<>();
        this.enrolledUsers = new ArrayList<>();

        for (Quiz quiz: cls.getQuizzes()) {
            quizzes.add(quiz.getId());
        }
        for (User user: cls.getUsers()) {
            enrolledUsers.add(user.getId());
        }
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public ArrayList<Integer> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void setEnrolledUsers(ArrayList<Integer> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public ArrayList<Integer> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Integer> quizzes) {
        this.quizzes = quizzes;
    }
}
