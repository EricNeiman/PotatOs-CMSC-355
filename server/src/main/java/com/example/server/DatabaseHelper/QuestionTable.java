package com.example.server.DatabaseHelper;

/**
 * Created by vita on 4/3/18.
 */

public class QuestionTable {
    public static final String TABLE_NAME = "Questions";
    public static final String COLUMN_QUESTION_ID = "QuestionID";
    public static final String COLUMN_PROMPT = "prompt";
    public static final String COLUMN_IMAGE_ID = "imageID";
    public static final String COLUMN_CORRECT = "correct";
    public static final String COLUMN_POINT_VALUE = "pointValue";
    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_PROMPT + " TEXT, " +
            COLUMN_IMAGE_ID + " INTEGER, " +
            COLUMN_CORRECT + " INTEGER, " +
            COLUMN_POINT_VALUE + " INTEGER);";
}
