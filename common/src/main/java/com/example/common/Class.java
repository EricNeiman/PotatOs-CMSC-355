package com.example.common;

public class Class {
    private User owner;
    private String className;
    private String classCode;
    private Quiz[] quizzes;
    private User[] users;

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

    public String getClassCode() {
        return classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
