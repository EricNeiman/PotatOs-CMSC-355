package com.example.server.DatabaseHelper;

/**
 * Created by vita on 4/3/18.
 */

public class ClassTable {
    public static final String CREATE_SQL = "CREATE TABLE Classes (ClassID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ownerID INTEGER NOT NULL, className TEXT, classCode TEXT);";
}
