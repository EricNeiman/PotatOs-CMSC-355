package com.example.common;

import com.example.common.REST.UserREST;

public class Class {
    private int ownerId; // class owner user object
    private String className; // class name text
    private String classCode; // class code text
    private Quiz[] quizzes; // quiz storage for class TODO switch to array list
    private User[] users; // user(student) storage for the class TODO switch to array list
    private int classID; //the class ID is assigned by the database and should be used in api calls

    public Class(int ownerId, String className, String classCode, Quiz[] quizzes, User[] users){
        this.ownerId = ownerId;
        this.className = className;
        this.classCode = classCode;
        this.quizzes = quizzes;
        this.users = users;
    } // basic constructor

    public Class() {
        this.ownerId = 0;
        this.className = "";
        this.classCode = "";
        this.quizzes = null;
        this.users = null;
        this.classID = 0;
    } // default constructor

    // getters and setters for each variable
    public User[] getUsers() {
        return users;
    }
    public void setUsers(User[] users) {
        this.users = users;
    }

    public Quiz[] getQuizzes() {
        return quizzes;
    }
    public void setQuizzes(Quiz[] quizzes) {
        this.quizzes = quizzes;
    }

    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int owner) {
        this.ownerId = owner;
    }

    public User getOwner() {
        return UserREST.getById(this.ownerId);
    }

    public String getClassCode() {
        return classCode;
    }
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }


    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

}