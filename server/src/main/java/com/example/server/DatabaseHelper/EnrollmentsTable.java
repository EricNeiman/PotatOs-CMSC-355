package com.example.server.DatabaseHelper;

import com.example.common.REST.SmallUser;
import com.example.common.User;
import com.example.common.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static void enrollUserInClass(User user, Class cls) throws SQLException {
        enrollUserInClass(user.getId(), cls.getClassID());
    }

    public static void enrollUserInClass(SmallUser user, int cls) throws SQLException {
        enrollUserInClass(user.getId(), cls);
    }

    public static void enrollUserInClass(int user, int cls) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        //if they are not already enrolled in the class, enroll them.
        PreparedStatement countq = db.prepareStatement("SELECT COUNT(*) FROM " +
                TABLE_NAME + " WHERE " + COLUMN_USER_ID + "=? AND " + COLUMN_CLASS_ID + "=?;");

        countq.setInt(1, user);
        countq.setInt(2, cls);
        ResultSet rs = countq.executeQuery();
        if (rs.getInt(1) == 0) {
            PreparedStatement query = db.prepareStatement(
                    "INSERT INTO " + EnrollmentsTable.TABLE_NAME + " (" +
                            EnrollmentsTable.COLUMN_USER_ID + ", " +
                            EnrollmentsTable.COLUMN_CLASS_ID + ") " +
                            " VALUES (?, ?);"
            );

            query.setInt(1, user);
            query.setInt(2, cls);
            query.execute();
        }
    }


    public static ArrayList<Integer> getEnrolledUsers(int classID) throws SQLException {
        Connection db = PotatOsDatabase.getDbConnection();

        PreparedStatement countq = db.prepareStatement("SELECT " + COLUMN_USER_ID + " FROM " +
                TABLE_NAME + " WHERE " + COLUMN_CLASS_ID + "=?;");

        countq.setInt(1, classID);
        ResultSet rs = countq.executeQuery();

        ArrayList<Integer> users = new ArrayList<>();
        while (rs.next()) {
            users.add(rs.getInt(1));
        }

        return users;
    }
}

