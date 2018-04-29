package com.example.server.DatabaseHelper;

import com.example.common.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnswerTable {
    public static final String COLUMN_ANSWER_ID = "answerId";
    public static final String COLUMN_LETTER = "letter";
    public static final String COLUMN_ANSWER_TEXT = "answerText";
    public static final String COLUMN_FEEDBACK = "feedback";
    public static final String COLUMN_CORRECT = "correct";
    public static final String COLUMN_SELECTED = "selected";
    public static final String COLUMN_QUESTION_ID = "questionId";
    public static final String TABLE_NAME = "Answers";

    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_LETTER + " TEXT, " +
            COLUMN_ANSWER_TEXT + " TEXT, " +
            COLUMN_FEEDBACK + " TEXT, " +
            COLUMN_CORRECT + " INTEGER, " +
            COLUMN_SELECTED + " INTEGER, " +
            COLUMN_QUESTION_ID + " INTEGER);";

    public static void createAnswer(Answer answer) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        PreparedStatement query = db.prepareStatement(
                "INSERT INTO " + TABLE_NAME + " (" +
                        COLUMN_LETTER + ", " +
                        COLUMN_ANSWER_TEXT + ", " +
                        COLUMN_FEEDBACK + ", " +
                        COLUMN_CORRECT + ", " +
                        COLUMN_SELECTED + ", " +
                        COLUMN_QUESTION_ID +
                        ") VALUES (?,?,?,?,?,?);"
        );

        query.setString(1, answer.getLetter());
        query.setString(2, answer.getAnswerText());
        query.setString(3, answer.getFeedback());
        query.setBoolean(4, answer.getCorrect());
        query.setBoolean(5, answer.getSelected());
        query.setInt(6, answer.getQuestionId());
        query.execute();

        answer.setId(query.getGeneratedKeys().getInt(1));
    }
}
