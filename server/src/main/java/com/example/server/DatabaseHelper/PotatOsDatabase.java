package com.example.server.DatabaseHelper;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PotatOsDatabase {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PotatOs.db";
    private static final String TABLE_NAME = "words";


    //the statements that should be executed to create a new database. order matters
    private static final String[] TABLE_CREATE_QUERIES =
    {
        UserTable.CREATE_SQL,
        ClassTable.CREATE_SQL,
        QuestionTable.CREATE_SQL,
        QuizTable.CREATE_SQL,
        EnrollmentsTable.CREATE_SQL,
    };

    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
    }

    public static void createTables() throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();
        Statement statement = db.createStatement();
        statement.setQueryTimeout(30);

        for (String query: TABLE_CREATE_QUERIES) {
            statement.execute(query);
        }
    }

    public static void deleteTables() {
        File file = new File("./" + DATABASE_NAME);
        if (file.delete()) {
            System.out.println("Database deleted.");
        } else {
            System.out.println("Delete operation failed");
        }
    }

    public static boolean checkTables() {
        //create the database if it doesn't already exist
        File f = new File(PotatOsDatabase.DATABASE_NAME);
        if (!f.exists()) {
            try {
                PotatOsDatabase.createTables();
                return true;
            } catch (SQLException ex) {
                System.out.println("Unable to create the tables... error message: ");
                ex.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }
}
