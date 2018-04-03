package com.example.server.DatabaseHelper;

/**
 * Created by vita on 4/3/18.
 */

public class QuestionTable {
    public static final String CREATE_SQL = "CREATE TABLE Questions (QuestionID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, prompt TEXT, imageID INTEGER, correct BOOL, pointValue INTEGER);";
}
