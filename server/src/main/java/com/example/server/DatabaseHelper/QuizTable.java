package com.example.server.DatabaseHelper;

import com.example.common.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vita on 4/3/18.
 */

public class QuizTable {


//    private String quizName; // quiz name text
//    private String password; // quiz password
//    private Question[] questions; // question storage TODO switch to array list
//    private Date closeTime; // date that quiz close
//    private Double timer; // quiz timer (uses fractional hours)
//    private User owner; // quiz owner object
//    private int pointsEarned; // points for correct answers TODO implement method to tally up earned points based on correct answers
//    private int pointsPossible; // points possible TODO implement method to tally up points based on questions' individual values
//    private Boolean submitted; // true if quiz was submitted on time


    public static final String TABLE_NAME = "Quizzes";
    public static final String COLUMN_QUIZ_ID = "quiId";
    public static final String COLUMN_QUIZ_NAME = "quizName";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CLOSE_TIME = "closeTime";
    public static final String COLUMN_TIMER = "timer";
    public static final String COLUMN_OWNER_USER_ID = "ownerUserId";
    public static final String COLUMN_SUBMITTED = "submitted";
    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_QUIZ_NAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_CLOSE_TIME + " INTEGER, " +
            COLUMN_TIMER + " REAL, " +
            COLUMN_OWNER_USER_ID + " INTEGER, " +
            COLUMN_SUBMITTED + " INTEGER);";



    public static void createQuiz(Quiz quiz)  throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        PreparedStatement query = db.prepareStatement(
                "INSERT INTO " + TABLE_NAME + " (" + COLUMN_QUIZ_NAME + ", " +
                        COLUMN_PASSWORD + ", " +
                        COLUMN_CLOSE_TIME + ", " +
                        COLUMN_TIMER + ", " +
                        COLUMN_OWNER_USER_ID + ", " +
                        COLUMN_SUBMITTED + ") VALUES (?,?,?,?,?,?);"
        );

        query.setString(1, quiz.getQuizName());
        query.setString(2, quiz.getPassword());
        query.setLong(3, quiz.getCloseTime().toInstant().getEpochSecond());
        query.setDouble(4, quiz.getTimer());
        query.setInt(5, quiz.getOwner().getId());
        query.setBoolean(6, quiz.getSubmitted());
        query.execute();

        quiz.setQuizId(query.getGeneratedKeys().getInt(1));
    }

    public static Quiz getQuizById(int id) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        PreparedStatement query = db.prepareStatement(
                "SELECT " + COLUMN_QUIZ_NAME + ", " +
                COLUMN_PASSWORD + ", " +
                COLUMN_CLOSE_TIME + ", " +
                COLUMN_TIMER + ", " +
                COLUMN_OWNER_USER_ID + ", " +
                COLUMN_SUBMITTED + " FROM " + TABLE_NAME + " WHERE " + COLUMN_QUIZ_ID + "=?;"
        );

        query.setInt(1, id);
        ResultSet rs = query.executeQuery();

        if (rs.next()) {
            Quiz quiz = new Quiz();
            quiz.setPassword(rs.getString(1));
            quiz.setCloseTime(rs.getDate(2));
            quiz.setTimer(rs.getDouble(3));
            quiz.setOwner(UserTable.getUserById(rs.getInt(4)));
            quiz.setSubmitted(rs.getBoolean(5));
            return quiz;
        } else {
            return null;
        }
    }
}
