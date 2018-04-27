package com.example.common;

import java.util.ArrayList;

public class User {
    private boolean isTeacher; // true if user is a teacher
    private ArrayList<Class> classesIn; // class user is in storage
    private ArrayList<Class> classesOwned; // owned class storage for user
    private String name; // user name
    private String email; // user email (used for sign in)
    private String passwordHash; // user password
    private int id; // user id

    public User(boolean isTeacher, ArrayList<Class> classesIn, ArrayList<Class> classesOwned, String name, String email, String passwordHash, int id){
        this.isTeacher = isTeacher;
        this.classesIn = classesIn;
        this.classesOwned = classesOwned;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.id = id;
    } // basic constructor

    public User(){
        this.isTeacher = false;
        this.classesIn = null;
        this.classesOwned = null;
        this.name = "";
        this.email = "";
        this.passwordHash = "";
        this.id = 0;
    } // default constructor

    // getters and setters for each variable
    public boolean getIsTeacher() {
        return isTeacher;
    }
    public void setIsTeacher(boolean isTeacher) {
        isTeacher = isTeacher;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Class> getClassesIn() {
        return classesIn;
    }
    public void setClassesIn(ArrayList<Class> classesIn) {
        this.classesIn = classesIn;
    }

    public ArrayList<Class> getClassesOwned() {
        return classesOwned;
    }
    public void setClassesOwned(ArrayList<Class> classesOwned) {
        this.classesOwned = classesOwned;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}