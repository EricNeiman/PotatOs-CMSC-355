package com.example.server.DatabaseHelper;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PotatOsDatabase {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PotatOs.db";
    private static final String TABLE_NAME = "words";

    //the statements that should be executed to create a new database. order matters
    private static final String[] TABLE_CREATE_QUERIES =
    {
        UserTable.CREATE_SQL,
        ClassTable.CREATE_SQL,
        QuestionTable.CREATE_SQL,
//        "CREATE TABLE UserEnrollments (EnrollmentID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, UserID INTEGER, ClassID INTEGER);",
        QuizTable.CREATE_SQL,
    };

    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
    }

    public void onCreate() throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();
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
