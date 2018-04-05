package com.example.server.DatabaseHelper;

public class EnrollmentsTable {
    int id;
    int userId;
    int classId;

    public static final String TABLE_NAME = "ClassEnrollments";
    public static final String COLUMN_ENROLLMENT_ID = "enrollmentID";
    public static final String COLUMN_USER_ID = "userID";
    public static final String COLUMN_CLASS_ID = "classID";
    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ENROLLMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_USER_ID + " INTEGER NOT NULL, " +
            COLUMN_CLASS_ID + " INTEGER NOT NULL);";
}
