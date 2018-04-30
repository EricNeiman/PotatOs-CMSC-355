package com.example.common;

import com.example.common.REST.ClassREST;
import com.example.common.REST.SmallUser;

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
        this.checkCollections();
    } // basic constructor

    public User(){
        this.isTeacher = false;
        this.name = "";
        this.email = "";
        this.passwordHash = "";
        this.id = 0;
        this.checkCollections();
    } // default constructor

    public User(SmallUser usr) {
        this.isTeacher = usr.isTeacher();
        this.email = usr.getEmail();
        this.id = usr.getId();
        this.name = usr.getName();
        this.passwordHash = usr.getPasswordHash();

        //private ArrayList<Class> classesIn; // class user is in storage
        //private ArrayList<Class> classesOwned; // owned class storage for user
        this.classesIn = new ArrayList<>();
        this.classesOwned = new ArrayList<>();

        for (int i: usr.getClassesIn()) {
            classesIn.add(ClassREST.getClassById(i));
        }

        for (int i: usr.getClassesOwned()) {
            classesOwned.add(ClassREST.getClassById(i));
        }
    }

    private void checkCollections() {
        if (this.classesIn == null) {
            this.classesIn = new ArrayList<>();
        }

        if (this.classesOwned == null) {
            this.classesOwned = new ArrayList<>();
        }
    }

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
    public void appendClassesIn(Class c) {
        this.classesIn.add(c);
    }

    public ArrayList<Class> getClassesOwned() {
        return classesOwned;
    }
    public void setClassesOwned(ArrayList<Class> classesOwned) {
        this.classesOwned = classesOwned;
    }
    public void appendClassesOwned(Class c) {
        this.classesOwned.add(c);
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