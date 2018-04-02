package com.example.server;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PotatOs.db";
    private static final String TABLE_NAME = "words";

//    private String prompt;
//    private QuizImage image;
//    private Boolean correct;
//    private Answer[] answers;
//    private int pointValue;

    //the statements that should be executed to create a new database. order matters
    private static final String[] TABLE_CREATE_QUERIES =
    {
        "CREATE TABLE Users (UserID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, passwordHash TEXT, email TEXT, name TEXT);",
        "CREATE TABLE Classes (ClassID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ownerID INTEGER NOT NULL, className TEXT, classCode TEXT);",
        "CREATE TABLE Questions (QuestionID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, prompt TEXT, imageID INTEGER, correct BOOL, pointValue INTEGER);"
    };

    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
    }

    public void onCreate() throws SQLException {
        Connection db = DatabaseHelper.getDbConnection();
        Statement statement = db.createStatement();
        statement.setQueryTimeout(30);

        for (String query: TABLE_CREATE_QUERIES) {
            statement.execute(query);
        }
    }

    public void onUpgrade() {
        File file = new File("./" + DATABASE_NAME);
        if (file.delete()) {
            System.out.println("Database deleted.");
        } else {
            System.out.println("Delete operation failed");
        }
    }
}
