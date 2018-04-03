package com.example.common;

public class Class {
    private User owner; // class owner user object
    private String className; // class name text
    private String classCode; // class code text
    private Quiz[] quizzes; // quiz storage for class TODO switch to array list
    private User[] users; // user(student) storage for the class TODO switch to array list

    public Class(User owner, String className, String classCode, Quiz[] quizzes, User[] users){
        this.owner = owner;
        this.className = className;
        this.classCode = classCode;
        this.quizzes = quizzes;
        this.users = users;
    } // basic constructor

    public Class() {
        this.owner = null;
        this.className = "";
        this.classCode = "";
        this.quizzes = null;
        this.users = null;
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

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
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
}