package com.example.common.REST;

import com.example.common.Class;
import com.example.common.User;

import java.util.ArrayList;

public class SmallUser {
    private boolean isTeacher; // true if user is a teacher
    private ArrayList<Integer> classesIn; // class user is in storage
    private String name; // user name
    private String email; // user email (used for sign in)
    private String passwordHash; // user password
    private int id; // user id


    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public ArrayList<Integer> getClassesIn() {
        return classesIn;
    }

    public void setClassesIn(ArrayList<Integer> classesIn) {
        this.classesIn = classesIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SmallUser(User user) {
        this.isTeacher = user.getIsTeacher();
        this.name = user.getName();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
        this.id = user.getId();

        this.classesIn = new ArrayList<>();
        for (Class cls: user.getClassesIn()) {
            this.classesIn.add(cls.getClassID());
        }

    }
}
