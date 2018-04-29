package com.example.server.DatabaseHelper;

import com.example.common.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by vita on 4/3/18.
 */

public class QuizTable {

//    private int quizId; //database id
//    private String quizName; // quiz name text
//    private String password; // quiz password

//    private Date openTime; // date that the quiz closes
//    private Date closeTime; // date that the quiz closes
//    private Double timer; // quiz timer (uses fractional hours)
//    private User owner; // quiz owner object
//    private Boolean submitted; // true if quiz was submitted on time


//    private ArrayList<Question> questions; // question storage


    public static final String TABLE_NAME = "Quizzes";
    public static final String COLUMN_QUIZ_ID = "quiId";
    public static final String COLUMN_QUIZ_NAME = "quizName";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_OPEN_TIME = "openTime";
    public static final String COLUMN_CLOSE_TIME = "closeTime";
    public static final String COLUMN_TIMER = "timer";
    public static final String COLUMN_OWNER_USER_ID = "ownerUserId";
    public static final String COLUMN_SUBMITTED = "submitted";
    public static final String COLUMN_CLASS_ID = "classId";

    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_QUIZ_NAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_CLOSE_TIME + " INTEGER, " +
            COLUMN_OPEN_TIME + " INTEGER, " +
            COLUMN_TIMER + " REAL, " +
            COLUMN_OWNER_USER_ID + " INTEGER, " +
            COLUMN_SUBMITTED + " INTEGER," +
            COLUMN_CLASS_ID + " INTEGER" + ");";



    public static void createQuiz(Quiz quiz)  throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        PreparedStatement query = db.prepareStatement(
                "INSERT INTO " + TABLE_NAME + " (" +
                        COLUMN_QUIZ_NAME + ", " +
                        COLUMN_PASSWORD + ", " +
                        COLUMN_CLOSE_TIME + ", " +
                        COLUMN_OPEN_TIME + ", " +
                        COLUMN_TIMER + ", " +
                        COLUMN_OWNER_USER_ID + ", " +
                        COLUMN_SUBMITTED + ") VALUES (?,?,?,?,?,?,?);"
        );

        query.setString(1, quiz.getQuizName());
        query.setString(2, quiz.getPassword());
        query.setDate(3, new java.sql.Date(quiz.getCloseTime().getTime()));
        query.setDate(4, new java.sql.Date(quiz.getOpenTime().getTime()));
        query.setDouble(5, quiz.getTimer());
        query.setInt(6, quiz.getOwner().getId());
        query.setBoolean(7, quiz.getSubmitted());
        query.execute();

        quiz.setId(query.getGeneratedKeys().getInt(1));
    }

    public static Quiz getQuizById(int id) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        PreparedStatement query = db.prepareStatement(
                "SELECT " +
                COLUMN_QUIZ_NAME + ", " +
                COLUMN_PASSWORD + ", " +
                COLUMN_CLOSE_TIME + ", " +
                COLUMN_OPEN_TIME + ", " +
                COLUMN_TIMER + ", " +
                COLUMN_OWNER_USER_ID + ", " +
                COLUMN_SUBMITTED + " FROM " + TABLE_NAME + " WHERE " + COLUMN_QUIZ_ID + "=?;"
        );

        query.setInt(1, id);
        ResultSet rs = query.executeQuery();

        if (rs.next()) {
            Quiz quiz = new Quiz();
            quiz.setQuizName(rs.getString(1));
            quiz.setPassword(rs.getString(2));
            quiz.setCloseTime(rs.getDate(3));
            quiz.setOpenTime(rs.getDate(4));
            quiz.setTimer(rs.getDouble(5));
            quiz.setOwner(UserTable.getUserById(rs.getInt(6)));
            quiz.setSubmitted(rs.getBoolean(7));
            return quiz;
        } else {
            return null;
        }
    }

    public static ArrayList<Integer> getQuizzesByClassId(int classID) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        PreparedStatement query = db.prepareStatement("SELECT " + COLUMN_QUIZ_ID + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_CLASS_ID + "=?;");

        query.setInt(1, classID);
        ResultSet rs = query.executeQuery();
        ArrayList<Integer> quizzes = new ArrayList<>();
        while (rs.next()) {
            quizzes.add(rs.getInt(1));
        }

        return quizzes;
    }
}
